package com.magicabc.magicabcapp.controller;

import com.magicabc.magicabcapp.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {
    private static Logger logger = LoggerFactory.getLogger(WebController.class);

    @RequestMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //a-zA-Z0-9的参数值，最多128字节
        String state = request.getParameter("state");
        String callbackUrl = WXConfiguration.MYSERVER+"authorize_callback";
        //snsapi_base 不弹出授权页面，直接跳转，只能获取用户openid
        response.sendRedirect(WXConfiguration.authorize.replace("REDIRECT_URI",callbackUrl).replace("STATE",state).replace("SCOPE",WXConfiguration.scope_snsapi_base));
       //snsapi_userinfo 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
        response.sendRedirect(WXConfiguration.authorize.replace("REDIRECT_URI",callbackUrl).replace("STATE",state).replace("SCOPE",WXConfiguration.scope_snsapi_userinfo));
    }

    @RequestMapping("/authorize_callback")
    public void authorize_callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String result = HttpRequest.sendGet(WXConfiguration.web_access_token.replace("CODE",code));
        JSONObject jsonObject = JSONObject.fromObject(result);
        String access_token = jsonObject.getString(ConstantUtil.access_token);
        String openid = jsonObject.getString("openid");//snsapi_base式的网页授权流程即到此为止
        String refresh_token = jsonObject.getString("refresh_token");
        //检验授权凭证（access_token）是否有效
        String isInvalid = HttpRequest.sendGet(WXConfiguration.isInvalid.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));
        JSONObject jsonObject4 = JSONObject.fromObject(isInvalid);
        if (jsonObject4.getInt("errcode") != 0){
            String result2 = HttpRequest.sendGet(WXConfiguration.refresh_token.replace("REFRESH_TOKEN",refresh_token));
            JSONObject jsonObject2 = JSONObject.fromObject(result2);
            access_token = jsonObject2.getString(ConstantUtil.access_token);
        }
        String result3 = HttpRequest.sendGet(WXConfiguration.sns_userInfo.replace("ACCESS_TOKEN",access_token).replace("OPENID",openid));
        JSONObject jsonObject3 = JSONObject.fromObject(result3);
        String nickname = jsonObject3.getString("nickname");
        String sex = jsonObject3.getString("sex");
        String province = jsonObject3.getString("province");
        String city = jsonObject3.getString("city");
        String country = jsonObject3.getString("country");
        String headimgurl = jsonObject3.getString("headimgurl");
        JSONArray privilege = jsonObject3.getJSONArray("privilege");
        //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
//        String unionid = jsonObject3.getString("unionid");
    }

    //jssdk初始化的签名
    @RequestMapping("/getSignature")
    @ResponseBody
    public JSONObject getSignature(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String url =request.getParameter("url");//签名用的url必须是调用JS接口页面的完整URL
        String noncestr = StringUtil.getGlobalId();
        Long timestamp = DateUtil.getTimestamp();
        String jsapi_ticket = TokenSingleton.getInstance().getMap().get("jsapi_ticket");
        Map<String,String> map=new HashMap<>();
        map.put(ConstantUtil.jsapi_ticket, jsapi_ticket);
        map.put("timestamp", timestamp+"");
        map.put("noncestr", noncestr);
        map.put("url", url);
        String string = EncryptUtil.sortForMap(map);
        String signature = EncryptUtil.Encrypt(string,"sha1");
        json.put("noncestr", noncestr);
        json.put("timestamp", timestamp);
        json.put("signature", signature);
        json.put("appId", WXConfiguration.APPID);
        return json;
    }

    @GetMapping("/init")
    public void init_get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String[] arr = new String[]{WXConfiguration.token,timestamp,nonce};
        Arrays.sort(arr);
        StringBuffer content = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }
        String sign = EncryptUtil.Encrypt(content.toString(),"sha1");
        PrintWriter out = response.getWriter();
        if(sign.equals(signature)){
            out.print(echostr);
        }
    }
    @PostMapping("/init")
    public void init_post(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Map<String,String> map = XmlUtil.xmlToMap(request);
            String ToUserName = map.get("ToUserName");
            String FromUserName = map.get("FromUserName");
			String CreateTime = map.get("CreateTime");
            String MsgType = map.get("MsgType");
			String MsgId = map.get("MsgId");
            String message = null;
            if(MessageUtil.MESSAGE_TEXT.equals(MsgType)){
                String Content = map.get("Content");
            }else if(MessageUtil.MESSAGE_IMAGE.equals(MsgType)){
                String PicUrl = map.get("PicUrl");
                String MediaId = map.get("MediaId");
            }else if(MessageUtil.MESSAGE_VOICE.equals(MsgType)){
                String MediaId = map.get("MediaId");
                String Format = map.get("Format");
                //开启语音识别后
                String Recognition = map.get("Recognition");
            }else if(MessageUtil.MESSAGE_VIDEO.equals(MsgType)){
                String MediaId = map.get("MediaId");
                String ThumbMediaId = map.get("ThumbMediaId");
            }else if(MessageUtil.MESSAGE_SHORT_VIDEO.equals(MsgType)){
                String MediaId = map.get("MediaId");
                String ThumbMediaId = map.get("ThumbMediaId");
            }else if(MessageUtil.MESSAGE_LOCATION.equals(MsgType)){
                String Location_X = map.get("Location_X");
                String Location_Y = map.get("Location_Y");
                String Scale = map.get("Scale");
                String Label = map.get("Label");
            }else if(MessageUtil.MESSAGE_LINK.equals(MsgType)){
                String Title = map.get("Title");
                String Description = map.get("Description");
                String Url = map.get("Url");
            }else if(MessageUtil.MESSAGE_EVENT.equals(MsgType)){
                String eventType = map.get("Event");
                if(MessageUtil.MESSAGE_EVENT_SUBSCRIBE.equals(eventType)){
                    //关注
                    //扫描带参数二维码事件，用户未关注时，进行关注后的事件推送
                    String EventKey = map.get("EventKey");
                    String Ticket = map.get("Ticket");
                }else if(MessageUtil.MESSAGE_EVENT_UNSUBSCRIBE.equals(eventType)){
                    //取消关注
                }else if(MessageUtil.MESSAGE_EVENT_SCAN.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String Ticket = map.get("Ticket");
                }else if(MessageUtil.MESSAGE_EVENT_LOCATION.equals(eventType)){
                    String Latitude = map.get("Latitude");
                    String Longitude = map.get("Longitude");
                    String Precision = map.get("Precision");
                }else if(MessageUtil.MESSAGE_EVENT_CLICK.equals(eventType)){
                    String EventKey = map.get("EventKey");

                }else if(MessageUtil.MESSAGE_EVENT_VIEW.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String MenuID = map.get("MenuID");

                }else if(MessageUtil.MESSAGE_SCANCODE_PUSH.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String ScanCodeInfo = map.get("ScanCodeInfo");
                    String ScanType = map.get("ScanType");
                    String ScanResult = map.get("ScanResult");
                }else if(MessageUtil.MESSAGE_SCANCODE_WAITMSG.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String ScanCodeInfo = map.get("ScanCodeInfo");
                    String ScanType = map.get("ScanType");
                    String ScanResult = map.get("ScanResult");
                }else if(MessageUtil.MESSAGE_PIC_SYSPHOTO.equals(eventType)||MessageUtil.MESSAGE_PIC_PHOTO_OR_ALBUM.equals(eventType)||MessageUtil.MESSAGE_PIC_WEIXIN.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String SendPicsInfo = map.get("SendPicsInfo");
                    String Count = map.get("Count");
                    String PicList = map.get("PicList");
                    String PicMd5Sum = map.get("PicMd5Sum");
                }else if(MessageUtil.MESSAGE_LOCATION_SELECT.equals(eventType)){
                    String EventKey = map.get("EventKey");
                    String SendLocationInfo = map.get("SendLocationInfo");
                    String Location_X = map.get("Location_X");
                    String Location_Y = map.get("Location_Y");
                    String Scale = map.get("Scale");
                    String Label = map.get("Label");
                    String Poiname = map.get("Poiname");
                }else if(MessageUtil.MESSAGE_MASSSENDJOBFINISH.equals(eventType)){
                    String MsgID = map.get("MsgID");
                    String Status = map.get("Status");
                    String TotalCount = map.get("TotalCount");
                    String FilterCount = map.get("FilterCount");
                    String SentCount = map.get("SentCount");
                    String ErrorCount = map.get("ErrorCount");
                    String ResultList = map.get("ResultList");
                    String ArticleIdx = map.get("ArticleIdx");
                    String UserDeclareState = map.get("UserDeclareState");
                    String AuditState = map.get("AuditState");
                    String OriginalArticleUrl = map.get("OriginalArticleUrl");
                    String OriginalArticleType = map.get("OriginalArticleType");
                    String CanReprint = map.get("CanReprint");
                    String NeedReplaceContent = map.get("NeedReplaceContent");
                    String NeedShowReprintSource = map.get("NeedShowReprintSource");
                    String CheckState = map.get("CheckState");
                }else if(MessageUtil.MESSAGE_EVENT_USER_AUTHORIZE_INVOICE.equals(eventType)){
                    String SuccOrderId = map.get("SuccOrderId");
                    String FailOrderId = map.get("FailOrderId");
                    //根据SuccOrderId查询出订单相关信息
                    String AuthorizeAppId = map.get("AuthorizeAppId");
                    String Source = map.get("Source");
                    String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
                    //7.发起开票请求
                    String param = TicketUtil.getMakeOutInvoiceParam(FromUserName,SuccOrderId);
                    String result = HttpRequest.sendPost(WXConfiguration.makeoutinvoice.replace("{access_token}",access_token),param);
                    int errcode = JSONObject.fromObject(result).getInt("errcode");
                    if(errcode==0){
                        logger.info("开票成功");
                    }
                }else if(MessageUtil.TEMPLATESENDJOBFINISH.equals(eventType)){
                    String MsgID = map.get("MsgID");
                    String Status = map.get("Status");
                }
            }
            logger.info(message);
            out.print(message);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }finally {
            out.close();
        }
    }
}
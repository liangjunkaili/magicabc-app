package com.magicabc.magicabcapp.controller;

import com.magicabc.magicabcapp.bean.ticket.AuthUrl;
import com.magicabc.magicabcapp.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    //公众号开票
    @GetMapping("/make_out_invoice_tencent")
    public void make_out_invoice_tencent(){
        //1.获取开票平台标识s_pappid
        String s_pappid = WXConfiguration.s_pappid;
        //2.获取access_token
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        //3.获取授权页ticket
        String api_ticket = TokenSingleton.getInstance().getMap().get(ConstantUtil.api_ticket);
        //4.获取授权页url
        String order_id = "订单id";
        int money = 10;
        int timestamp = (int) (DateUtil.getTimestamp()/1000);
        String source = "web";//app：app开票，web：微信h5开票，wxa：小程序开发票
        String redirect_url = "";//授权成功后跳转页面
        int type = 0;//0：开票授权，1：填写字段开票授权，2：领票授权
        AuthUrl authUrl = new AuthUrl();
        authUrl.setMoney(money);
        authUrl.setOrder_id(order_id);
        authUrl.setRedirect_url(redirect_url);
        authUrl.setS_pappid(s_pappid);
        authUrl.setSource(source);
        authUrl.setTicket(api_ticket);
        authUrl.setType(type);
        authUrl.setTimestamp(timestamp);
        String json = JSONObject.fromObject(authUrl).toString();
        //获取授权页链接
        String  result = HttpRequest.sendPost(WXConfiguration.getauthurl.replace("{access_token}",access_token),json);
        String auth_url = JSONObject.fromObject(result).getString("auth_url");
    }

    @PostMapping("/platform_setpdf")
    public void platform_setpdf(@RequestParam("file")MultipartFile file){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String response = HttpRequest.sendPostUploadFile(WXConfiguration.platform_setpdf.replace("{access_token}",accessToken).replace("TYPE",file.getContentType()),file);
        logger.info(response);
    }

    @PostMapping("/platform_getpdf")
    public void platform_getpdf(){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String param = "{" +
                "    \"action\": \"get_url\"," +
                "    \"s_media_id\": \"75195574948725301\"" +
                "}";
        String response = HttpRequest.sendPost(WXConfiguration.platform_getpdf.replace("{access_token}",accessToken),param);
        logger.info(response);
    }
    //http://域名/card_code_decrypt?encrypt_code=ENCRYPT_CODE&card_id=CARDID
    @PostMapping("/card_code_decrypt")
    public void card_code_decrypt(HttpServletRequest request){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String encrypt_code = request.getParameter("encrypt_code");
        String param = "{\"encrypt_code\":\""+encrypt_code+"\"}";
        String response = HttpRequest.sendPost(WXConfiguration.card_code_decrypt.replace("{access_token}",accessToken),param);
        logger.info(response);
    }
    //查询授权完成状态
    @PostMapping("/getauthdata")
    public void getauthdata(){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String param = "{" +
                "\"s_pappid\": \"{s_pappid}\"," +
                "\"order_id\": \"{order_id}\"" +
                "}";
        String response = HttpRequest.sendPost(WXConfiguration.getauthdata.replace("{access_token}",accessToken),param);
        logger.info(response);
    }
    //拒绝开票
    @PostMapping("/rejectinsert")
    public void rejectinsert(){
        String accessToken = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String param = "{\n" +
                "    \"s_pappid\": \"d3JCEfhGLW+q0iGP+o9\",\n" +
                "    \"order_id\": \"111229\",\n" +
                "    \"reason\": \"1234\",\n" +
                "    url\": \"http://xxx.com\"\n" +
                "}";
        String response = HttpRequest.sendPost(WXConfiguration.rejectinsert.replace("{access_token}",accessToken),param);
        logger.info(response);
    }
}

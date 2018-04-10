package com.magicabc.magicabcapp.util;

import com.magicabc.magicabcapp.bean.coupon.*;
import com.magicabc.magicabcapp.bean.ticket.*;
import com.magicabc.magicabcapp.bean.ticket.Invoice;
import com.magicabc.magicabcapp.bean.ticket.InvoiceInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.util.ArrayList;
import java.util.List;

public class TicketUtil {

    public static String getMakeOutInvoiceParam(String wxopenid,String ddh){
        Invoice invoice = new Invoice();
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setWxopenid(wxopenid);//用户的openid
        invoiceInfo.setDdh(ddh);//订单号，企业自己内部的订单号码
        invoiceInfo.setFpqqlsh(StringUtil.getGlobalId());//发票请求流水号，唯一查询发票的流水号
        invoiceInfo.setNsrsbh("");//纳税人识别码
        invoiceInfo.setNsrmc("");//纳税人名称
        invoiceInfo.setNsrdz("");//纳税人地址
        invoiceInfo.setNsrdh("");//纳税人电话
        invoiceInfo.setNsrbank("");//纳税人开户行
        invoiceInfo.setNsrbankid("");//纳税人银行账号
        invoiceInfo.setGhfmc("");//购货方名称
        invoiceInfo.setGhfnsrsbh("");//购货方识别号
        invoiceInfo.setGhfdz("");//购货方地址
        invoiceInfo.setGhfdh("");//购货方电话
        invoiceInfo.setGhfbank("");//购货方开户行
        invoiceInfo.setGhfbankid("");//购货方银行帐号
        invoiceInfo.setKpr("");//开票人
        invoiceInfo.setSkr("");//收款人
        invoiceInfo.setFhr("");//复核人
        invoiceInfo.setJshj("");//价税合计
        invoiceInfo.setHjse("");//合计金额
        invoiceInfo.setBz("");//备注
        invoiceInfo.setHylx("");//行业类型 0 商业 1其它
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setFphxz("");//发票行性质 0 正常 1折扣 2 被折扣
        invoiceDetail.setSpbm("");//商品编码
        invoiceDetail.setXmmc("");//项目名称
        invoiceDetail.setDw("");//计量单位
        invoiceDetail.setGgxh("");//规格型号
        invoiceDetail.setXmsl("");//项目数量
        invoiceDetail.setXmdj("");//项目单价
        invoiceDetail.setXmje("");//项目金额 不含税，单位元 两位小数
        invoiceDetail.setSl("");//税率 精确到两位小数 如0.01
        invoiceDetail.setSe("");//税额 单位元 两位小数
        invoiceDetails.add(invoiceDetail);
        invoiceInfo.setInvoicedetail_list(invoiceDetails);
        invoice.setInvoiceinfo(invoiceInfo);
        return JSONObject.fromObject(invoice,JsonUtil.getJsonConfig()).toString();
    }
    public static void clearoutinvoice(){
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setWxopenid("");
        invoiceInfo.setFpqqlsh("");
        invoiceInfo.setNsrsbh("");
        invoiceInfo.setNsrmc("");
        invoiceInfo.setYfpdm("");
        invoiceInfo.setYfphm("");
        String param = JSONObject.fromObject(invoiceInfo,JsonUtil.getJsonConfig()).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String result = HttpRequest.sendPost(WXConfiguration.clearoutinvoice.replace("{access_token}",access_token),param);
    }
    public static void queryinvoceinfo(){
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setFpqqlsh("");
        invoiceInfo.setNsrsbh("");
        String param = JSONObject.fromObject(invoiceInfo,JsonUtil.getJsonConfig()).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String result = HttpRequest.sendPost(WXConfiguration.queryinvoceinfo.replace("{access_token}",access_token),param);
    }
    //1.获取自身的开票平台识别码{
    public static String gets_pappid(){
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String result = HttpRequest.sendPost(WXConfiguration.seturl.replace("{access_token}",access_token),"{}");
        String invoice_url = JSONObject.fromObject(result).getString("invoice_url");
        String s_pappid = invoice_url.split("&")[1].split("=")[1];
        return s_pappid;
    }

    //2.创建发票卡券模板
    public static String getCard_id(){
        com.magicabc.magicabcapp.bean.coupon.Invoice invoice = new com.magicabc.magicabcapp.bean.coupon.Invoice();
        com.magicabc.magicabcapp.bean.coupon.InvoiceInfo invoiceInfo = new com.magicabc.magicabcapp.bean.coupon.InvoiceInfo();
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setLogo_url("");//发票商家 LOGO ，请参考 上传图片接口
        baseInfo.setTitle("");//收款方（显示在列表），上限为 9 个汉字，建议填入商户简称
        baseInfo.setCustom_url_name("");//开票平台自定义入口名称，与 custom_url 字段共同使用，长度限制在 5 个汉字内
        baseInfo.setCustom_url("");//开票平台自定义入口跳转外链的地址链接 , 发票外跳的链接会带有发票参数，用于标识是从哪张发票跳出的链接
        baseInfo.setCustom_url_sub_title("");//显示在入口右侧的 tips ，长度限制在 6 个汉字内
        baseInfo.setPromotion_url_name("");//营销场景的自定义入口
        baseInfo.setPromotion_url("");//入口跳转外链的地址链接，发票外跳的链接会带有发票参数，用于标识是从那张发票跳出的链接
        baseInfo.setPromotion_url_sub_title("");//显示在入口右侧的 tips ，长度限制在 6 个汉字内
        invoiceInfo.setBase_info(baseInfo);
        invoiceInfo.setPayee("");//收款方（开票方）全称，显示在发票详情内。故建议一个收款方对应一个发票卡券模板
        invoiceInfo.setType("");//发票类型
        invoice.setInvoice_info(invoiceInfo);
        String param = JSONObject.fromObject(invoice).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String result = HttpRequest.sendPost(WXConfiguration.platform_createcard.replace("{access_token}",access_token),param);
        return JSONObject.fromObject(result).getString("card_id");
    }
    //5.将电子发票卡券插入用户卡包
    public static void insertUserCardBag(){
        Card card = new Card();
        CardExt cardExt = new CardExt();
        UserCard userCard = new UserCard();
        InvoiceUserData invoiceUserData = new InvoiceUserData();
        invoiceUserData.setFee(1);//发票的金额，以分为单位
        invoiceUserData.setTitle("");//发票的抬头
        invoiceUserData.setBilling_time(1);//发票的开票时间，为10位时间戳（utc+8）
        invoiceUserData.setBilling_no("");//发票的发票代码
        invoiceUserData.setBilling_code("");//发票的发票号码
        invoiceUserData.setFee_without_tax(1);//不含税金额，以分为单位
        invoiceUserData.setTax(1);//税额，以分为单位
        invoiceUserData.setS_pdf_media_id("");//发票pdf文件上传到微信发票平台后，会生成一个发票s_media_id，该s_media_id可以直接用于关联发票PDF和发票卡券。发票上传参考“ 3 上传PDF ”一节
        invoiceUserData.setS_trip_pdf_media_id("");//其它消费附件的PDF，如行程单、水单等，PDF上传方式参考“ 3 上传PDF ”一节
        invoiceUserData.setCheck_code("");//校验码，发票pdf右上角，开票日期下的校验码
        invoiceUserData.setBuyer_number("");//购买方纳税人识别号
        invoiceUserData.setBuyer_address_and_phone("");//购买方地址、电话
        invoiceUserData.setBuyer_bank_account("");//购买方开户行及账号
        invoiceUserData.setSeller_number("");//销售方纳税人识别号
        invoiceUserData.setSeller_address_and_phone("");//销售方地址、电话
        invoiceUserData.setSeller_bank_account("");//销售方开户行及账号
        invoiceUserData.setRemarks("");//备注，发票右下角初
        invoiceUserData.setCashier("");//收款人，发票左下角处
        invoiceUserData.setMaker("");//开票人，发票下方处
        List<Info> infos = new ArrayList<>();
        Info info = new Info();
        info.setName("");//项目的名称
        info.setNum(1);//项目的数量
        info.setUnit("");//项目的单位，如个
        info.setPrice(1);//项目的单价
        infos.add(info);
        invoiceUserData.setInfo(infos);
        userCard.setInvoice_user_data(invoiceUserData);
        cardExt.setNonce_str("");//随机字符串，防止重复
        cardExt.setUser_card(userCard);
        card.setAppid("");//该订单号授权时使用的appid，一般为商户appid
        card.setCard_ext(cardExt);
        card.setCard_id("");//发票card_id
        card.setOrder_id("");//发票order_id，既商户给用户授权开票的订单号
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"reimburse_status","code"});
        String param = JSONObject.fromObject(card,jsonConfig).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        String result = HttpRequest.sendPost(WXConfiguration.insert.replace("{access_token}",access_token),param);
        JSONObject.fromObject(result).getString("code");
        JSONObject.fromObject(result).getString("openid");
    }

    //6.更新发票卡券状态
    public static void updateCardStaus(){
        Card card = new Card();
        card.setCard_id("");//发票 id
        card.setCode("");//发票 code
        card.setReimburse_status("INVOICE_REIMBURSE_CANCEL");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"appid","order_id","card_ext"});
        String param = JSONObject.fromObject(card,jsonConfig).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.updatestatus.replace("{access_token}",access_token),param);
    }

    //设置授权页字段信息
    public static void setbizattr_set_auth_field(){
        Auth auth = new Auth();
        String param = JSONObject.fromObject(auth,JsonUtil.getJsonConfig()).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.setbizattr_set_auth_field.replace("{access_token}",access_token),param);
    }
    //查询授权页字段信息
    public static void setbizattr_get_auth_field(){
        String param = "{}";
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.setbizattr_get_auth_field.replace("{access_token}",access_token),param);
    }
    //关联商户号与开票平台
    public static void setbizattr_set_pay_mch(){
        Paymch paymch = new Paymch();
        String param = JSONObject.fromObject(paymch,JsonUtil.getJsonConfig()).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.setbizattr_set_pay_mch.replace("{access_token}",access_token),param);
    }
    //查询商户号与开票平台关联情况
    public static void setbizattr_get_pay_mch(){
        String param = "{}";
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.setbizattr_get_pay_mch.replace("{access_token}",access_token),param);
    }
    //查询报销发票信息
    public static void reimburse_getinvoiceinfo(){
        Item item = new Item();
        item.setCard_id("sss");
        item.setEncrypt_code("jljk");
        String param = JSONObject.fromObject(item).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.reimburse_getinvoiceinfo.replace("{access_token}",access_token),param);
    }
    //查询报销发票信息
    public static void reimburse_getinvoicebatch(){
        Item item = new Item();
        item.setCard_id("sss");
        item.setEncrypt_code("jljk");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(item);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("item_list",jsonArray);
        String param = jsonObject.toString();
        System.out.print(param);
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.reimburse_getinvoicebatch.replace("{access_token}",access_token),param);
    }
    //报销方更新发票状态
    public static void reimburse_updateinvoicestatus(){
        Item item = new Item();
        item.setCard_id("sss");
        item.setEncrypt_code("jljk");
        item.setReimburse_status("sss");
        String param = JSONObject.fromObject(item).toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.reimburse_updateinvoicestatus.replace("{access_token}",access_token),param);
    }
    //报销方批量更新发票状态
    public static void reimburse_updatestatusbatch(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("openid","openid");
        jsonObject.put("reimburse_status","reimburse_status");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("card_id","card_id");
        jsonObject2.put("encrypt_code","encrypt_code");
        jsonArray.add(jsonObject2);
        jsonObject.put("invoice_list",jsonArray);
        String param = jsonObject.toString();
        String access_token = TokenSingleton.getInstance().getMap().get(ConstantUtil.access_token);
        HttpRequest.sendPost(WXConfiguration.reimburse_updatestatusbatch.replace("{access_token}",access_token),param);
    }
    public static void main(String[] args){
//        updateCardStaus();
        reimburse_updatestatusbatch();
    }
}
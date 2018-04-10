package com.magicabc.magicabcapp.util;

import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;

import java.util.List;

public class MessageUtil {

	//接收普通消息
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_SHORT_VIDEO = "shortvideo";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_LINK = "link";
	//接收事件推送
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_EVENT_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_EVENT_SCAN = "SCAN";
	public static final String MESSAGE_EVENT_LOCATION = "LOCATION";
	public static final String MESSAGE_EVENT_CLICK = "CLICK";
	public static final String MESSAGE_EVENT_VIEW = "VIEW";
	public static final String MESSAGE_EVENT_USER_AUTHORIZE_INVOICE = "user_authorize_invoice";
	//被动回复用户消息（包括上面的text、image、voicevideo）
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_NEWS = "news";

	public static final String MESSAGE_SCANCODE_PUSH = "scancode_push";
	public static final String MESSAGE_SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String MESSAGE_PIC_SYSPHOTO = "pic_sysphoto";
	public static final String MESSAGE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String MESSAGE_PIC_WEIXIN = "pic_weixin";
	public static final String MESSAGE_LOCATION_SELECT = "location_select";
	public static final String MESSAGE_MASSSENDJOBFINISH = "MASSSENDJOBFINISH";
	public static final String TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";

}
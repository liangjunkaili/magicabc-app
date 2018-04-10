package com.magicabc.magicabcapp.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XmlUtil {
    public static Map<String,String> xmlStrToMap(String xmlStr) throws Exception {
        Map<String,String> map = new HashMap<>();
        Document document = DocumentHelper.parseText(xmlStr);
        Element root = document.getRootElement();
        Iterator<Element> rootItor = root.elementIterator();
        while (rootItor.hasNext()){
            Element element = rootItor.next();
            map.put(element.getName(),element.getText());
        }
        return map;
    }
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws Exception {
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        for(Element e: list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }
}

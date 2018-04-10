package com.magicabc.magicabcapp.controller;

import com.magicabc.magicabcapp.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HtmlController {

    @GetMapping("/templates")
    String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "index";
    }
    @GetMapping("/share")
    String share(HttpServletRequest request) {
        //逻辑处理
        return "share";
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        List<Map<String, Object>> list = new ArrayList<>();
        String[] tableHeaders = new String[3];
        tableHeaders[0] = "zhanghao";
        tableHeaders[1] = "nickname";
        tableHeaders[2] = "password";
        try {
            /*for (KfAccount kfAccount:kfAccounts){
                Map<String, Object> map = ExcelUtil.objectToMap(kfAccount);
                list.add(map);
            }*/
            OutputStream stream = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=template.xls");
            ExcelUtil.createExcel(list,tableHeaders,stream);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/importExcel")
    @ResponseBody
    public void importExcel(@RequestParam("file")MultipartFile file){
        try {
            InputStream inputStream = file.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(bufferedInputStream);
            HSSFWorkbook workbook = new HSSFWorkbook(poifsFileSystem);
            ExcelUtil.analysisExcel(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.magicabc.magicabcapp.controller;

import com.magicabc.magicabcapp.bean.Msg;
import com.magicabc.magicabcapp.bean.SysUser;
import com.magicabc.magicabcapp.util.ExcelUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping("/templates")
    String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "index";
    }
    @RequestMapping(value ="/welcome", method = RequestMethod.GET)
    String welcome() {
        return "welcome";
    }
    @RequestMapping(value ="/login", method = RequestMethod.GET)
    @ResponseBody
    JSONObject login(@AuthenticationPrincipal User user,@RequestParam(value = "logout",required = false) String logout,HttpServletRequest request,HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        if (logout != null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            jsonObject.put("username","");
            jsonObject.put("authorities","");
        }else {
            jsonObject.put("username",user.getUsername());
            jsonObject.put("authorities",user.getAuthorities());
        }
        return jsonObject;
    }
    @GetMapping("/share")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String share(HttpServletRequest request) {
        //逻辑处理
        System.out.println("login fail...");
        return "share";
    }
    @RequestMapping("/logout")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
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

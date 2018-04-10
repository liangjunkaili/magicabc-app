package com.magicabc.magicabcapp.util;

import org.apache.poi.hssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HSSF - Horrible Spreadsheet Format
 */
public class ExcelUtil {
    public static void createExcel(List<Map<String,Object>> list, String[] tableHeaders, OutputStream stream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        HSSFHeader header = sheet.getHeader();
        header.setLeft("left");
        header.setCenter("center");
        header.setRight("right");
        HSSFFooter footer = sheet.getFooter();
        footer.setCenter("center");
        footer.setLeft("left");
        footer.setRight("right");
        int rows = list.size()+1;
        int columns = tableHeaders.length;
        //创建表头
        HSSFRow tableHeader = sheet.createRow(0);
        for (int k=0;k<columns;k++){
            HSSFCell cell = tableHeader.createCell(k, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(tableHeaders[k]);
        }
        for (int i=1;i<rows;i++){
            HSSFRow row = sheet.createRow(i);
            Map<String,Object> map = list.get(i-1);
            Object[] keys = map.keySet().toArray();
            for (int j=0;j<columns;j++){
                HSSFCell cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue((String) map.get(keys[j]));
            }
        }
        workbook.write(stream);
    }

    public static void analysisExcel(HSSFWorkbook workbook){
        int sheets = workbook.getNumberOfSheets();
        for (int i=0;i<sheets;i++){
            HSSFSheet sheet = workbook.getSheetAt(i);
            int rowNum = sheet.getLastRowNum();
            for (int j=1;j<=rowNum;j++){
                HSSFRow row = sheet.getRow(j);
                String account = row.getCell(0).getStringCellValue();
                String nickName = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                System.out.println(account+"--"+nickName+"--"+password);
            }
        }
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}

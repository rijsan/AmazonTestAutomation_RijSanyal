package com.ApiTestAutomation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private String filePath;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }
    public Map<String, String> getData(String sheetName) throws IOException {
        Map<String, String> data = new HashMap<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        Row row = sheet.getRow(0);
        Row dataRow = sheet.getRow(1);

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell keyCell = row.getCell(i);
            Cell valueCell = dataRow.getCell(i);
            data.put(keyCell.getStringCellValue(), valueCell.toString());
        }
        workbook.close();
        return data;
    }
}

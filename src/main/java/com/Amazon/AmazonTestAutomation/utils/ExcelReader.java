package com.Amazon.AmazonTestAutomation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;

    public ExcelReader(String excelFilePath) throws IOException {
        FileInputStream fis = new FileInputStream(excelFilePath);
        workbook = new XSSFWorkbook(fis);
    }

    public void selectSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    public String getData(String columnHeading, String rowHeading) {
        int rowNum = getRowNumber(rowHeading);
        int colNum = getColumnNumber(columnHeading);

        if (rowNum != -1 && colNum != -1) {
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);
            return getCellValue(cell);
        }
        return null;
    }

    private int getRowNumber(String rowHeading) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // Assuming row headings are in the first column
            if (getCellValue(cell).equalsIgnoreCase(rowHeading)) {
                return row.getRowNum();
            }
        }
        return -1;
    }

    private int getColumnNumber(String columnHeading) {
        Row firstRow = sheet.getRow(0); // Assuming column headings are in the first row
        for (Cell cell : firstRow) {
            if (getCellValue(cell).equalsIgnoreCase(columnHeading)) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }

    private String getCellValue(Cell cell) {
        if(cell==null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }

    public void close() throws IOException {
        workbook.close();
    }
}

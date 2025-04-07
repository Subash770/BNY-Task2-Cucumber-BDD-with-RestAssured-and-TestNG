package com.example.task2.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    public static List<Map<String, String>> getTestData(String sheetName) {
        List<Map<String, String>> testData = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File("src/test/resources/excel/ProductTestData.xlsx"));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int numOfColumns = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Map<String, String> dataMap = new LinkedHashMap<>();
                Row currentRow = sheet.getRow(i);

                for (int j = 0; j < numOfColumns; j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    Cell valueCell = currentRow.getCell(j);
                    String value = valueCell == null ? "" : valueCell.toString();
                    dataMap.put(key.trim(), value.trim());
                }

                testData.add(dataMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return testData;
    }
}


package com.concordia.microservices.fruit_month_service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;

@Component
public class ExcelToH2Importer implements CommandLineRunner {

    @Override
    public void run(String... args) {
        String excelPath = "E:/Concordia/Summer 25/DSD/Assignment 2/FMP.xlsx";
        String jdbcUrl = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int idCounter = 10001;

        try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
             FileInputStream fis = new FileInputStream(new File(excelPath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String fruit = row.getCell(0).getStringCellValue();

                for (int j = 1; j <= 12; j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null || cell.getCellType() == CellType.BLANK) continue;

                    double fmp = cell.getNumericCellValue();
                    String month = months[j - 1];

                    try (PreparedStatement stmt = conn.prepareStatement(
                            "INSERT INTO FRUIT_MONTH_PRICE (id, fruit, \"month\", fmp, environment) VALUES (?, ?, ?, ?, ?)") ) {

                        stmt.setInt(1, idCounter++);
                        stmt.setString(2, fruit);
                        stmt.setString(3, month);
                        stmt.setDouble(4, fmp);
                        stmt.setString(5, "");  // environment

                        stmt.executeUpdate();
                    }
                }
            }

            System.out.println("Data successfully inserted into H2 database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

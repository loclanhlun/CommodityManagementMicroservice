package com.commoditymanagement.userservice;

import com.commoditymanagement.core.data.ImportBill;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream importBillToExcel(List<ImportBill> importBill)throws IOException {
        String[] COLUMNS = {"Id","Người nhập", "Nhà cung cấp", "Tên kho", "Ngày nhập", "Tổng giá"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();){

            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("ImportBill");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNS[col]);
                cell.setCellStyle(headerCellStyle);
            }
            int rowIdx = 1;
            for (ImportBill item : importBill) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(item.getId());
                row.createCell(1).setCellValue(item.getUser().getFullName());
                row.createCell(2).setCellValue(item.getSupplier().getName());
                row.createCell(3).setCellValue(item.getWarehouses().getName());
                row.createCell(4).setCellValue(item.getImportDate());
                row.createCell(5).setCellValue(String.valueOf(item.getTotalPrice()));
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}

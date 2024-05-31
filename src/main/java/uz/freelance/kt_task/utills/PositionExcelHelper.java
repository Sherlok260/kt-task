package uz.freelance.kt_task.utills;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.freelance.kt_task.entity.Position;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PositionExcelHelper {

    private static final String[] EN_HEADERS = {"ID", "Name", "Description", "Department", "Salary", "Level"};
    private static final String[] RU_HEADERS = {"ID", "Название", "Описание", "Отдел", "Зарплата", "Уровень"};
    private static final String[] UZ_HEADERS = {"ID", "Nomi", "Tavsif", "Bo'lim", "Maosh", "Daraja"};

    public static ByteArrayInputStream positionsToExcel(List<Position> positions, String language) {
        String[] headers;
        if (language != null) {
            if (language.toLowerCase(Locale.ROOT).contains("ru")) {
                headers = RU_HEADERS;
            } else if (language.toLowerCase(Locale.ROOT).contains("uz")) {
                headers = UZ_HEADERS;
            } else {
                headers = EN_HEADERS;
            }
        } else {
            headers = EN_HEADERS;
        }

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Positions");

            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            // Data
            int rowIdx = 1;
            for (Position position : positions) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(position.getId());
                row.createCell(1).setCellValue(position.getName());
                row.createCell(2).setCellValue(position.getDescription());
                row.createCell(3).setCellValue(position.getDepartment());
                row.createCell(4).setCellValue(position.getSalary());
                row.createCell(5).setCellValue(position.getLevel());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file: " + e.getMessage());
        }
    }
}

package uz.freelance.kt_task.utills;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uz.freelance.kt_task.entity.Employee;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EmployeeExcelHelper {

    private static final String[] EN_HEADERS = {"ID", "First Name", "Last Name", "Email", "Phone", "Hire Date", "Position"};
    private static final String[] RU_HEADERS = {"ID", "Имя", "Фамилия", "Email", "Телефон", "Дата приёма на работу", "Должность"};
    private static final String[] UZ_HEADERS = {"ID", "Ism", "Familiya", "Email", "Telefon", "Ishga kirgan sana", "Lavozim"};

    public static ByteArrayInputStream employeesToExcel(List<Employee> employees, String language) {
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
            Sheet sheet = workbook.createSheet("Employees");

            // Header
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < headers.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(headers[col]);
            }

            // Data
            int rowIdx = 1;
            for (Employee employee : employees) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(employee.getId());
                row.createCell(1).setCellValue(employee.getFirstName());
                row.createCell(2).setCellValue(employee.getLastName());
                row.createCell(3).setCellValue(employee.getEmail());
                row.createCell(4).setCellValue(employee.getPhone());
                row.createCell(5).setCellValue(employee.getHireDate().toString());
                row.createCell(6).setCellValue(employee.getPosition().getName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file: " + e.getMessage());
        }
    }
}

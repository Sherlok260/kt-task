package uz.freelance.kt_task.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.freelance.kt_task.entity.Employee;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.EmployeeDto;
import uz.freelance.kt_task.service.EmployeeService;
import uz.freelance.kt_task.utills.EmployeeExcelHelper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public HttpEntity<?> createPosition(@Valid  @RequestBody EmployeeDto employeeDto) {
        ApiResponse apiResponse = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllEmployees(Pageable pageable) {
        ApiResponse apiResponse = employeeService.getAllEmployees(pageable);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto, @RequestParam Long employeeId) {
        ApiResponse apiResponse = employeeService.updateEmployee(employeeDto, employeeId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getEmployeeById(@PathVariable Long id) {
        ApiResponse apiResponse = employeeService.getEmployeeById(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteEmployee(@PathVariable Long id) {
        ApiResponse apiResponse = employeeService.deleteEmployee(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/multiDelete")
    public ResponseEntity<?> deleteEmployees(@Valid @RequestBody List<Long> ids) {
        ApiResponse apiResponse = employeeService.deleteEmployees(ids);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping("/export")
    public void exportEmployeesToExcel(HttpServletResponse response, @RequestHeader("Accept-Language") String language) throws IOException, IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");
        List<Employee> employees = ((Page<Employee>) employeeService.getAllEmployees(Pageable.unpaged()).getData()).getContent();
        ByteArrayInputStream stream = EmployeeExcelHelper.employeesToExcel(employees, language);
        IOUtils.copy(stream, response.getOutputStream());
    }

}

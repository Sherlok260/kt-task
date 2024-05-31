package uz.freelance.kt_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.EmployeeDto;
import uz.freelance.kt_task.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public HttpEntity<?> createPosition(@RequestBody EmployeeDto employeeDto) {
        ApiResponse apiResponse = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllEmployees(Pageable pageable) {
        ApiResponse apiResponse = employeeService.getAllEmployees(pageable);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @RequestParam Long employeeId) {
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
    public ResponseEntity<Void> deleteEmployees(@RequestBody List<Long> ids) {
        ApiResponse apiResponse = employeeService.deleteEmployees(ids);
        return ResponseEntity.status(apiResponse.getStatus()).build();
    }

}

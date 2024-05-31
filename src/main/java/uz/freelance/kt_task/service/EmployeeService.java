package uz.freelance.kt_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.freelance.kt_task.entity.Employee;
import uz.freelance.kt_task.entity.Position;
import uz.freelance.kt_task.payload.ApiResponse;
import uz.freelance.kt_task.payload.EmployeeDto;
import uz.freelance.kt_task.repository.EmployeeRepository;
import uz.freelance.kt_task.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    public ApiResponse createEmployee(EmployeeDto dto) {
        try {
            Long positionId = dto.getPosition_id();
            Optional<Position> optionalPosition = positionRepository.findById(positionId);
            if (optionalPosition.isEmpty()) {
                return new ApiResponse("position not found", 404);
            }
            Employee employee = new Employee();
            employee.convertDtoToEntity(dto);
            employee.setPosition(optionalPosition.get());
            Employee save = employeeRepository.save(employee);
            return new ApiResponse("success create employee", 201, save);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse getEmployeeById(Long id) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (optionalEmployee.isEmpty()) {
                return new ApiResponse("employee not found", 404);
            }
            Employee employee = optionalEmployee.get();
            return new ApiResponse("success get employee", 200, employee);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse getAllEmployees(Pageable pageable) {
        try {
            Page<Employee> employees = employeeRepository.findAll(pageable);
            return new ApiResponse("employees", 200, employees);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse updateEmployee(EmployeeDto dto, Long employeeId) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            if (optionalEmployee.isEmpty()) {
                return new ApiResponse("employee not found", 404);
            }
            Employee employee = optionalEmployee.get();
            employee.convertDtoToEntity(dto);
            Employee save = employeeRepository.save(employee);
            return new ApiResponse("success update employee", 201, save);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse deleteEmployee(Long id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return new ApiResponse("success delete employee", 201);
            }
            return new ApiResponse("employee not found", 404);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

    public ApiResponse deleteEmployees(List<Long> ids) {
        try {
            employeeRepository.deleteAllById(ids);
            return new ApiResponse("success delete employees", 201);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("some error", 500, e.getMessage());
        }
    }

}

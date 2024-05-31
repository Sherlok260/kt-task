package uz.freelance.kt_task.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;
}

package uz.freelance.kt_task.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {
    @NotBlank(message = "firstName cannot be blank")
    private String firstName;

    @NotBlank(message = "lastName cannot be blank")
    private String lastName;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "phone cannot be blank")
    private String phone;

    @NotBlank(message = "hireDate cannot be blank")
    private LocalDate hireDate;

    @NotBlank(message = "position id cannot be blank")
    private Long position_id;
}

package uz.freelance.kt_task.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PositionDto {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotBlank(message = "department cannot be blank")
    private String department;

    @NotNull(message = "salary cannot be blank")
    private Double salary;

    @NotBlank(message = "level cannot be blank")
    private String level;
}
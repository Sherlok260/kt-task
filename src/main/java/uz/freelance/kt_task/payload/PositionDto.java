package uz.freelance.kt_task.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PositionDto {
    private String name;
    private String description;
    private String department;
    private Double salary;
    private String level;
}
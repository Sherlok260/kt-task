package uz.freelance.kt_task.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.freelance.kt_task.payload.PositionDto;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String department;
    private Double salary;
    private String level;

    @OneToMany(mappedBy = "position")
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();

    public void convertDtoToEntity(PositionDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.department = dto.getDepartment();
        this.salary = dto.getSalary();
        this.level = dto.getLevel();
    }
}

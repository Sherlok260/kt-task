package uz.freelance.kt_task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.freelance.kt_task.payload.EmployeeDto;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "position_id")
    @JsonBackReference
    private Position position;

    public void convertDtoToEntity(EmployeeDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.hireDate = dto.getHireDate();
    }

}

package uz.freelance.kt_task.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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
    private Position position;

}

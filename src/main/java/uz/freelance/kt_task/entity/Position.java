package uz.freelance.kt_task.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
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
    private List<Employee> employees;
}

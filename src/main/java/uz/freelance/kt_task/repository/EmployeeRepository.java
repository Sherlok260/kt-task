package uz.freelance.kt_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.freelance.kt_task.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

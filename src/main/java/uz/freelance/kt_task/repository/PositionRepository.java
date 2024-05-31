package uz.freelance.kt_task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.freelance.kt_task.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}

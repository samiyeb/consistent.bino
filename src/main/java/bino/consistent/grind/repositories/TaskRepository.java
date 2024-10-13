package bino.consistent.grind.repositories;
import bino.consistent.grind.entities.*;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByGoal(Goal goal);
}

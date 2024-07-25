package bino.consistent.grind.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import bino.consistent.grind.entities.*;


public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long userId);
}

package bino.consistent.grind.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bino.consistent.grind.entities.*;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByTaskId(Long taskId);
}

package bino.consistent.grind.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import bino.consistent.grind.entities.*;
public interface TaskRepository extends JpaRepository<Task, Long> {}

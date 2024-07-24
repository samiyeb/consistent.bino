package bino.consistent.grind.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import bino.consistent.grind.entities.*;
public interface GoalRepository extends JpaRepository<Goal, Long> {}

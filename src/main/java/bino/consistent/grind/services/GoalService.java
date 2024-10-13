package bino.consistent.grind.services;

import bino.consistent.grind.repositories.GoalRepository;
import bino.consistent.grind.entities.Goal;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    private final GoalRepository goalRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    public Goal findById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Goal not found with id: " + id));
    }

    public Goal progress(Long id) {
        Goal goal = findById(id);
        goal.setProgression();
        return update(goal, id);
    }

    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal update(@Valid Goal newGoal, Long id) {
        return goalRepository.findById(id).map(goal -> {
            // You might want to update fields from newGoal to goal here
            goal.setTitle(newGoal.getTitle());
            goal.setDescription(newGoal.getDescription());
            goal.setProgression(); // Assuming you want to update progression too
            return goalRepository.save(goal);
        }).orElseGet(() -> {
            newGoal.setId(id); // Set the id for the new goal
            return goalRepository.save(newGoal);
        });
    }

    public void delete(Long id) {
        goalRepository.deleteById(id);
    }

    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }
}



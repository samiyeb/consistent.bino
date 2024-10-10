package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import bino.consistent.grind.entities.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class GoalService {
    @Autowired
    private final GoalRepository goalRepository;

    GoalService(GoalRepository goalRepository){
        this.goalRepository = goalRepository;
    }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    // public Goal findById(@PathVariable Long id) {
    //     return goalRepository.findById(id).get();
    // }

    public Goal progress(@PathVariable Long id) {
        Goal goal = goalRepository.findById(id).get();
        goal.setProgression();
        return this.update(goal, id);

    }

    public Goal create(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal update(@Valid @RequestBody Goal newGoal, @PathVariable Long id) {
        return goalRepository.findById(id).map(goal -> {
            return goalRepository.save(goal);
        })
        .orElseGet(() -> {
            return goalRepository.save(newGoal);
        });
    }

    public void delete(@PathVariable Long id) {
        goalRepository.deleteById(id);
    }

    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    public Goal findById(Long id) {
        return goalRepository.findById(id).orElse(null);
    }
}


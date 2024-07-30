package bino.consistent.grind.controllers;
import bino.consistent.grind.entities.*;
import bino.consistent.grind.services.*;
import jakarta.validation.Valid;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    // @Autowired
    // private GoalService goalService;

    // @PostMapping
    // public Goal createGoal(@RequestBody Goal goal) {
    //     return goalService.createGoal(goal);
    // }

    // @GetMapping("/user/{userId}")
    // public List<Goal> getGoalsByUser(@PathVariable Long userId) {
    //     return goalService.getGoalsByUserId(userId);
    // }

    // @DeleteMapping
    // public void deleteGoal(@RequestBody Goal goal){
    //     goalService.deleteGoal(goal);
    // }

    @Autowired
    private GoalService goalService;

    GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<Goal> findAll() {
        return goalService.findAll();
    }

    @GetMapping("/{id}")
    public Goal findById(@PathVariable Long id) {
        return goalService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Goal create(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Goal goal, @PathVariable Long id) {
        goalService.update(goal,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        goalService.delete(id);
    }

    // Other goal-related endpoints
}


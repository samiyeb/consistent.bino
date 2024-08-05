package bino.consistent.grind.controllers;
import bino.consistent.grind.entities.*;
import bino.consistent.grind.services.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    @Autowired
    private final GoalService goalService;

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
}


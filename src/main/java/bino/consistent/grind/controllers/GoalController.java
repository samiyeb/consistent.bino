package bino.consistent.grind.controllers;
import bino.consistent.grind.entities.*;
import bino.consistent.grind.services.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    @Autowired
    private final GoalService goalService;

    @Autowired
    private TaskService taskService;

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

    @GetMapping("/{id}/progress")
    public Goal progress(@PathVariable Long id) {
        return goalService.progress(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Goal create(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Goal update(@Valid @RequestBody Goal goal, @PathVariable Long id) {
        return goalService.update(goal,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        goalService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<Task>> getTasksForGoal(@PathVariable Long id) {
        Goal goal = goalService.findById(id);
        if (goal != null) {
            List<Task> tasks = taskService.findByGoal(goal);
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/tasks")
    public ResponseEntity<Task> addTaskToGoal(@PathVariable Long id, @RequestBody Task task) {
        Goal goal = goalService.findById(id);
        if (goal != null) {
            task.setGoal(goal); // Associate task with goal
            Task createdTask = taskService.save(task); // Save the task
            return ResponseEntity.ok(createdTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


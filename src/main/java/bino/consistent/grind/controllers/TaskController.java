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
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public Task update(@Valid @RequestBody Task task, @PathVariable Long id) {
        return taskService.update(task,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/complete")
    public Task complete(@PathVariable Long id) {
        return taskService.complete(id);
    }



    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    // Mark a task as complete or incomplete
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> toggleTaskCompletion(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if (task != null) {
            task.setCompleted(!task.isCompleted()); // Toggle completion status
            Task updatedTask = taskService.save(task);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

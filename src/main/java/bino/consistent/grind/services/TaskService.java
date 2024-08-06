package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import bino.consistent.grind.entities.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(@PathVariable Long id) {
        return taskRepository.findById(id).get();
    }

    
    public Task create(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    public Task update(@Valid @RequestBody Task newTask, @PathVariable Long id) {
        return taskRepository.findById(id).map(task -> {
            return taskRepository.save(task);
        })
        .orElseGet(() -> {
            return taskRepository.save(newTask);
        });
    }

    public Task complete(@PathVariable Long id) {
        Task task = taskRepository.findById(id).orElseThrow();

        if (!(task.isCompleted())) {
            task.setCompleted(true);
        }

        return this.update(task, id);
    }
    
    public void delete(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}

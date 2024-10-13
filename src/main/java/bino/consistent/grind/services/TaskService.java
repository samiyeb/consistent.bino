package bino.consistent.grind.services;

import bino.consistent.grind.repositories.TaskRepository;
import bino.consistent.grind.entities.Task;
import bino.consistent.grind.entities.Goal;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + id));
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(@Valid Task newTask, Long id) {
        return taskRepository.findById(id).map(task -> {
            // Update fields from newTask to task here
            task.setTitle(newTask.getTitle());
            task.setDescription(newTask.getDescription());
            task.setCompleted(newTask.isCompleted()); // Assuming you want to update completion status
            return taskRepository.save(task);
        }).orElseGet(() -> {
            newTask.setId(id); // Set the id for the new task
            return taskRepository.save(newTask);
        });
    }

    public Task complete(Long id) {
        Task task = findById(id);
        task.setCompleted(true);
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findByGoal(Goal goal) {
        return taskRepository.findByGoal(goal);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
}


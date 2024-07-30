package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import jakarta.validation.Valid;
import bino.consistent.grind.entities.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    // GoalService(GoalRepository goalRepository){
    //     this.goalRepository = goalRepository;
    // }

    // public Goal createGoal(Goal goal) {
    //     return goalRepository.save(goal);
    // }

    // public List<Goal> getGoalsByUserId(Long userId) {
    //     return goalRepository.findByUserId(userId);
    // }

    // public void deleteGoal(Integer id) {
    //     goalRepository.delete();
    // }

    public List<Goal> findAll() {
        return goalRepository.findAll();
    }

    public Goal findById(@PathVariable Long id) {
        return goalRepository.findById(id).get();
    }

    
    public Goal create(@RequestBody Goal goal) {
        return goalRepository.save(goal);
    }

    
    public void update(@Valid @RequestBody Goal goal, @PathVariable Long id) {
        if (goalRepository.existsById(id)){
            this.delete(id);
            this.create(goal);
        }
        else {
            this.create(goal);
        }
    }

    
    public void delete(@PathVariable Long id) {
        goalRepository.deleteById(id);
    }

    // Other goal-related methods
}


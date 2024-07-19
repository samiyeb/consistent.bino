package bino.consistent.grind.goals;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private final GoalRepository goalRepository;

    GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @GetMapping
    public List<Goal> retrieveAllGoals(){
        return goalRepository.retrieveAllGoals();
    }

    @GetMapping("/{id}")
    public Goal retrieveGoal(@PathVariable Integer id){
        Optional<Goal> goal = goalRepository.retrieveGoal(id);
        if(goal.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Goal not found.");
        }

        return goal.get();
        
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Goal goal){
        goalRepository.create(goal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Goal goal, @PathVariable Integer id){
        goalRepository.update(goal, id);
        
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        goalRepository.delete(id);
    }

}

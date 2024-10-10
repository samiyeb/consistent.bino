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
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private GoalService goalService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User user, @PathVariable Long id) {
        return userService.update(user,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    // Create a new goal for a specific user
    @PostMapping("/{id}/goals")
    public ResponseEntity<Goal> addGoalToUser(@PathVariable Long id, @RequestBody Goal goal) {
        User user = userService.findById(id);
        if (user != null) {
            goal.setUser(user); // Associate goal with user
            Goal createdGoal = goalService.save(goal); // Save the goal
            return ResponseEntity.ok(createdGoal);
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }

    @GetMapping("/{id}/goals")
    public List<Goal>  getGoals(@PathVariable Long id) {
        return userService.findById(id).getGoals();
    }

}

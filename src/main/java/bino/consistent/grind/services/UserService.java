package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import bino.consistent.grind.entities.*;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    public User update(@Valid @RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            return userRepository.save(user);
        })
        .orElseGet(() -> {
            return userRepository.save(newUser);
        });
    }

    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

}

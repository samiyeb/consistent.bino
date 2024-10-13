package bino.consistent.grind.services;

import bino.consistent.grind.repositories.UserRepository;
import bino.consistent.grind.entities.User;

import jakarta.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(@Valid User newUser, Long id) {
        return userRepository.findById(id).map(user -> {
            // Update fields from newUser to user here
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id); // Set the id for the new user
            return userRepository.save(newUser);
        });
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}


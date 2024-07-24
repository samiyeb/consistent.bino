package bino.consistent.grind.services;
import bino.consistent.grind.repositories.*;
import bino.consistent.grind.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

}

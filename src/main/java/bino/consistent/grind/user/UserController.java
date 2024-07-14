package bino.consistent.grind.user;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRestClient client;

    UserController(UserRestClient client) {
        this.client = client;
    }

    @GetMapping("")
    public List<User> findAll() {
        return client.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return client.findById(id);
    }

}

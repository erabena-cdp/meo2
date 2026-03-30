package com.hua.yes.domain;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getUsers() {
        return userRepository.findAllByFNameIsNotNull();
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return userRepository.save(user);
    }
}
package aop.practice.controller;

import aop.practice.domain.User;
import aop.practice.repository.UserRepository;
import aop.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //controller는 json이 지원이 안되는것 같다
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @GetMapping("/user/{userId}")
    public User findById(@PathVariable Long userId) {
        return userService.findUser(userId);
    }
    @PostMapping ("/user")
    public void signUp(@RequestBody User user) {
        userService.signup(user);
        System.out.println("success");

    }
}

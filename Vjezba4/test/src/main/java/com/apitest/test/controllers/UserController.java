package com.apitest.test.controllers;

import com.apitest.test.models.Address;
import com.apitest.test.models.User;
import com.apitest.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getusers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return (userService.getUserById(id));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/user")
    public List<User> createUser(@RequestBody User user) {
        return (userService.createUser(user));
    }

    @PutMapping("/user/{id}")
    public User updateAddress(@PathVariable Long id, @RequestBody User user){
        return (userService.updateUser(id, user));
    }
}

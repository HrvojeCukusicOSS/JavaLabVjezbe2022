package com.apitest.test.controllers;

import com.apitest.test.models.User;
import com.apitest.test.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("")
    public List<User> createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.readAllUser();
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Long id){
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}

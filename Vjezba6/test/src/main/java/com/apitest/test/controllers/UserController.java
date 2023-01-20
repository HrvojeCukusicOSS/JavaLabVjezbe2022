package com.apitest.test.controllers;

import com.apitest.test.dtos.UserDto;
import com.apitest.test.models.User;
import com.apitest.test.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("")
    public List<UserDto> createUser(@RequestBody UserDto user){
        return userService.addUser(user);
    }

    @GetMapping("")
    public List<UserDto> getAllUsers(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "field") String field, @RequestParam(name = "direction") String direction)
    {
        Page<UserDto> resultPage = userService.findPaginated(page, size, field, direction);

        return resultPage.getContent();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody UserDto user,@PathVariable Long id){
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    public List<UserDto> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}

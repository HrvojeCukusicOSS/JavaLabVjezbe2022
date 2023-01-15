package com.apitest.test.services;

import com.apitest.test.models.User;
import com.apitest.test.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> addUser(User user){
        userRepository.save(user);
        return userRepository.findAll();
    }

    public List<User> readAllUser(){
        return userRepository.findAll();
    }

    public User updateUser(User user, Long id){
        User toUpdate = userRepository.findById(id).orElse(null);
        toUpdate.setName(user.getName());
        toUpdate.setDevices(user.getDevices());
        toUpdate.setAddress(user.getAddress());
        userRepository.save(toUpdate);
        return userRepository.findById(id).orElse(null);
    }

    public List<User> deleteUser(Long id){
        userRepository.deleteById(id);
        return userRepository.findAll();
    }
}

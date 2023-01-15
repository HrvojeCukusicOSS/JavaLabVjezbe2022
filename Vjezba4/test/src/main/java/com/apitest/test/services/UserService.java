package com.apitest.test.services;

import com.apitest.test.models.User;
import com.apitest.test.repositories.AddressRepository;
import com.apitest.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<User> createUser(User user){
        var address = addressRepository.findById(user.getAddress().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid address"));
        user.setAddress(address);
        userRepository.save(user);
        return getUsers();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
    }

    public  User updateUser(Long id, User user){
        var toUpdate = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        toUpdate.setName(user.getName());
        var address = addressRepository.findById(user.getAddress().getId()).orElse(null);
        addressRepository.save(address);
        toUpdate.setAddress(address);
        return userRepository.save(toUpdate);
    }
}

package com.apitest.test.services;

import com.apitest.test.dtos.UserDto;
import com.apitest.test.mappers.UserMapper;
import com.apitest.test.models.Device;
import com.apitest.test.models.User;
import com.apitest.test.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private UserMapper userMapper;

    public List<UserDto> addUser(UserDto user){
        userRepository.save(userMapper.mapDtoToEntity(user));
        return userMapper.mapMultipleEntityToDto((List<User>) userRepository.findAll());
    }

    public List<UserDto> readAllUser(){
        return userMapper.mapMultipleEntityToDto((List<User>) userRepository.findAll());
    }

    public Page<UserDto> findPaginated(int page, int size, String field, String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageRequest = createPageRequest(page, size, sort);

        Page<User> searchResultPage = userRepository.findAll(pageRequest);

        return userMapper.mapEntityPageIntoDTOPage( pageRequest, searchResultPage);
    }

    private Pageable createPageRequest(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return pageable;
    }

    public UserDto updateUser(UserDto user, Long id){
        User toUpdate = userRepository.findById(id).orElse(null);
        toUpdate.setName(user.getName());
        toUpdate.setDevices((Set<Device>) user.getDevices());
        userRepository.save(toUpdate);
        return userMapper.mapEntityToDto(userRepository.findById(id).orElse(null));
    }

    public List<UserDto> deleteUser(Long id){
        userRepository.deleteById(id);
        return userMapper.mapMultipleEntityToDto((List<User>) userRepository.findAll());
    }
}

package com.apitest.test.mappers;

import com.apitest.test.dtos.UserDto;
import com.apitest.test.models.Device;
import com.apitest.test.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto mapEntityToDto(User user) {
        var userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setDevices(user.getDevices().stream().collect(Collectors.toList()));

        return userDto;
    }

    public List<UserDto> mapMultipleEntityToDto(List<User> users) {
        if(users != null){
            List<UserDto> toReturn = new ArrayList<>();
            for (var user:users) {
                var userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setDevices(user.getDevices().stream().collect(Collectors.toList()));
                toReturn.add(userDto);
            }
            return toReturn;
        }
        return null;
    }

    public User mapDtoToEntity(UserDto userDto) {
        var user = new User();

        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setDevices((Set<Device>) userDto.getDevices());

        return user;
    }

    public Page<UserDto> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<User> source) {
        List<UserDto> dtos = mapMultipleEntityToDto(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}

package com.apitest.test.dtos;

import com.apitest.test.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String number;
    private String city;
    private List<User> users;
}

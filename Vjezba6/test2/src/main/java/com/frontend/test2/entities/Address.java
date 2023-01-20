package com.frontend.test2.entities;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Address {
    private Long id;

    private String number;

    private String city;

    private Set<User> users;
}

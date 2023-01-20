package com.frontend.test2.entities;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private Address address;
    private Set<Device> devices;
}

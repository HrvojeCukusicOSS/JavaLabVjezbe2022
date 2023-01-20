package com.frontend.test2.entities;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Device {
    private Long id;
    private String name;
    private Set<Measurement> values;
    private User user;
}

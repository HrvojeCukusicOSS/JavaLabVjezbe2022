package com.frontend.test2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Measurement {

    private Long id;
    private int value;
    private String measurement_unit;
    private LocalDate date;
    private Device device;
}

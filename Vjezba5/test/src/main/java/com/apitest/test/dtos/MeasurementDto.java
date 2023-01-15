package com.apitest.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {
    private Long id;

    private int value;

    private String measurement_unit;

    private LocalDate date;
    private Long deviceId;
}

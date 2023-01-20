package com.apitest.test.dtos;


import com.apitest.test.models.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

    private Long id;
    private List<Measurement> values;
    private String Name;
}

package com.apitest.test.controllers;

import com.apitest.test.dtos.MeasurementDto;
import com.apitest.test.services.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/measurement")
public class MeasurementClass {
    private MeasurementService MeasurementService;

    @PostMapping("")
    public List<MeasurementDto> createMeasurement(@RequestBody MeasurementDto measurementDto){
        return MeasurementService.addMeasurement(measurementDto);
    }

    @GetMapping("")
    public List<MeasurementDto> getAllMeasurements(){
        return MeasurementService.readAllMeasurement();
    }

    @PutMapping("/{id}")
    public MeasurementDto updateMeasurement(@RequestBody MeasurementDto measurementDto,@PathVariable Long id){
        return MeasurementService.updateMeasurement(measurementDto, id);
    }

    @DeleteMapping("/{id}")
    public List<MeasurementDto> deleteMeasurement(@PathVariable Long id){
        return MeasurementService.deleteMeasurement(id);
    }
}

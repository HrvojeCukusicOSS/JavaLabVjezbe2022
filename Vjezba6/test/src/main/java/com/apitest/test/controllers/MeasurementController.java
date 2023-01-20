package com.apitest.test.controllers;

import com.apitest.test.dtos.MeasurementDto;
import com.apitest.test.dtos.UserDto;
import com.apitest.test.services.MeasurementService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {
    private MeasurementService measurementService;

    @PostMapping("")
    public List<MeasurementDto> createMeasurement(@RequestBody MeasurementDto measurementDto){
        return measurementService.addMeasurement(measurementDto);
    }

    @GetMapping("")
    public List<MeasurementDto> getAllMeasurements(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "field") String field, @RequestParam(name = "direction") String direction)
    {
        Page<MeasurementDto> resultPage = measurementService.findPaginated(page, size, field, direction);

        return resultPage.getContent();
    }

    @PutMapping("/{id}")
    public MeasurementDto updateMeasurement(@RequestBody MeasurementDto measurementDto,@PathVariable Long id){
        return measurementService.updateMeasurement(measurementDto, id);
    }

    @DeleteMapping("/{id}")
    public List<MeasurementDto> deleteMeasurement(@PathVariable Long id){
        return measurementService.deleteMeasurement(id);
    }
}

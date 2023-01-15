package com.apitest.test.services;

import com.apitest.test.dtos.MeasurementDto;
import com.apitest.test.mappers.MeasurementMapper;
import com.apitest.test.models.Measurement;
import com.apitest.test.repositories.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MeasurementService {
    private MeasurementRepository measurementRepository;
    private MeasurementMapper measurementMapper;

    public List<MeasurementDto> addMeasurement(MeasurementDto measurementDto){
        var tempMeasurement =  measurementRepository.findAll().stream().filter(m -> m.getDate().getYear() == measurementDto.getDate().getYear() && m.getDate().getMonthValue() == measurementDto.getDate().getMonthValue()).toList();
        final var test = tempMeasurement.stream().filter(v -> v.getDevice().getId().equals(measurementDto.getDeviceId())).toList();
        if (test.size() == 0) {
            measurementRepository.save(measurementMapper.mapDtoToEntity(measurementDto));
            return measurementMapper.mapMultipleEntityToDto(measurementRepository.findAll());
        }
        return null;
    }

    public List<MeasurementDto> readAllMeasurement(){
        return measurementMapper.mapMultipleEntityToDto(measurementRepository.findAll());
    }

    public MeasurementDto updateMeasurement(MeasurementDto measurement, Long id){
        Measurement toUpdate = measurementRepository.findById(id).orElse(null);
        toUpdate.setValue(measurement.getValue());
        toUpdate.setMeasurement_unit(measurement.getMeasurement_unit());
        toUpdate.setDate(measurement.getDate());
        measurementRepository.save(toUpdate);
        return measurementMapper.mapEntityToDto(measurementRepository.findById(id).orElse(null));
    }

    public List<MeasurementDto> deleteMeasurement(Long id){
        measurementRepository.deleteById(id);
        return measurementMapper.mapMultipleEntityToDto(measurementRepository.findAll());
    }
}

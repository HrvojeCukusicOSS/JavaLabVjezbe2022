package com.apitest.test.mappers;

import com.apitest.test.dtos.MeasurementDto;
import com.apitest.test.models.Measurement;
import com.apitest.test.repositories.DeviceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MeasurementMapper {
    private final DeviceRepository deviceRepository;

    public MeasurementMapper(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public MeasurementDto mapEntityToDto(Measurement measurement) {
        var measurementDto = new MeasurementDto();

        measurementDto.setId(measurement.getId());
        measurementDto.setValue(measurement.getValue());
        measurementDto.setMeasurement_unit(measurement.getMeasurement_unit());
        measurementDto.setDate(measurement.getDate());
        measurementDto.setDeviceId(measurementDto.getDeviceId());

        return measurementDto;
    }

    public List<MeasurementDto> mapMultipleEntityToDto(List<Measurement> measurements) {
        if(measurements != null){
            List<MeasurementDto> toReturn = new ArrayList<>();
            for (var measurement:measurements) {
                var measurementDto = new MeasurementDto();
                measurementDto.setId(measurement.getId());
                measurementDto.setValue(measurement.getValue());
                measurementDto.setMeasurement_unit(measurement.getMeasurement_unit());
                measurementDto.setDate(measurement.getDate());
                measurementDto.setDeviceId(measurementDto.getDeviceId());
                toReturn.add(measurementDto);
            }

            return toReturn;
        }
        return null;
    }
    public Measurement mapDtoToEntity(MeasurementDto measurementDto) {
        var measurement = new Measurement();

        measurement.setId(measurementDto.getId());
        measurement.setValue(measurementDto.getValue());
        measurement.setMeasurement_unit(measurementDto.getMeasurement_unit());
        measurement.setDate(measurementDto.getDate());
        measurement.setDevice(deviceRepository.findById(measurementDto.getDeviceId()).orElse(null));

        return measurement;
    }

    public Page<MeasurementDto> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Measurement> source) {
        List<MeasurementDto> dtos = mapMultipleEntityToDto(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}

package com.apitest.test.services;

import com.apitest.test.dtos.DeviceDto;
import com.apitest.test.dtos.MeasurementDto;
import com.apitest.test.mappers.DeviceMapper;
import com.apitest.test.mappers.MeasurementMapper;
import com.apitest.test.models.Device;
import com.apitest.test.models.Measurement;
import com.apitest.test.repositories.DeviceRepository;
import com.apitest.test.repositories.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceService {
    private DeviceRepository deviceRepository;
    private MeasurementRepository measurementRepository;
    private MeasurementMapper measurementMapper;
    private DeviceMapper deviceMapper;

    public List<DeviceDto> addDevice(DeviceDto device){
        deviceRepository.save(deviceMapper.mapDtoToEntity(device));
        var toReturn = deviceMapper.mapMultipleEntityToDto(deviceRepository.findAll());
        return toReturn;
    }

    public List<DeviceDto> readAllDevice(){
        return deviceMapper.mapMultipleEntityToDto(deviceRepository.findAll());
    }

    public DeviceDto updateDevice(DeviceDto device, Long id){
        Device toUpdate = deviceRepository.findById(id).orElse(null);
        toUpdate.setValues(Set.copyOf(device.getValues()));
        toUpdate.setName(device.getName());
        deviceRepository.save(toUpdate);
        return deviceMapper.mapEntityToDto(deviceRepository.findById(id).orElse(null));
    }

    public List<DeviceDto> deleteDevice(Long id){
        deviceRepository.deleteById(id);
        return deviceMapper.mapMultipleEntityToDto(deviceRepository.findAll());
    }

    public DeviceDto getYearlyValue(Long id, int year){
        var tempDevice = deviceRepository.findById(id).orElse(null);
        var tempMeasurementList = measurementRepository.findAllByDeviceId(id).stream().filter(v -> v.getDate().getYear() == year).toList();
        var totalMeasurementSum = tempMeasurementList.stream().mapToInt(v -> v.getValue()).sum();
        var measurementToInput = new MeasurementDto();
        measurementToInput.setId(Long.parseLong("1"));
        measurementToInput.setDate(LocalDate.of(year, 12, 31));
        measurementToInput.setValue(totalMeasurementSum);
        measurementToInput.setMeasurement_unit(tempMeasurementList.get(tempMeasurementList.size() - 1).getMeasurement_unit());
        measurementToInput.setDeviceId(id);
        List<Measurement> list = new ArrayList<>();
        list.add(measurementMapper.mapDtoToEntity(measurementToInput));
        return new DeviceDto(id, list, tempDevice.getName());
    }

    public DeviceDto getMonthsInAYearValue(Long id, int year){
        var tempDevice = deviceRepository.findById(id).orElse(null);
        var tempMeasurementList = measurementRepository.findAllByDeviceId(id).stream().filter(v -> v.getDate().getYear() == year).toList();
        return new DeviceDto(id, tempMeasurementList, tempDevice.getName());
    }

    public DeviceDto getValueByMonthInYear(Long id, int year, String month){
        var tempDevice = deviceRepository.findById(id).orElse(null);
        var numberOfMonth = valueOfMonth(month);
        var tempMeasurement = measurementRepository.findAllByDeviceId(id).stream().filter(v -> v.getDate().getYear() == year && v.getDate().getMonthValue() == numberOfMonth).toList();
        return new DeviceDto(id, tempMeasurement, tempDevice.getName());
    }

    private int valueOfMonth (String month){
        switch(month){
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 0;
        }
    }
}

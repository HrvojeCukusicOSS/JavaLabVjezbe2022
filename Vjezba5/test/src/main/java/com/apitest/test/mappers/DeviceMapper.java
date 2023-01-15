package com.apitest.test.mappers;

import com.apitest.test.dtos.DeviceDto;
import com.apitest.test.models.Device;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DeviceMapper {
    public DeviceDto mapEntityToDto(Device device) {
        var deviceDto = new DeviceDto();

        deviceDto.setId(device.getId());
        deviceDto.setValues(device.getValues().stream().collect(Collectors.toList()));
        deviceDto.setName(device.getName());

        return deviceDto;
    }

    public List<DeviceDto> mapMultipleEntityToDto(List<Device> devices) {
        if(devices != null){
            List<DeviceDto> toReturn = new ArrayList<>();
            for (var device:devices) {
                var deviceDto = new DeviceDto();
                deviceDto.setId(device.getId());
                deviceDto.setValues(device.getValues().stream().collect(Collectors.toList()));
                toReturn.add(deviceDto);
            }

            return toReturn;
        }
        return null;
    }

    public Device mapDtoToEntity(DeviceDto deviceDto) {
        var device = new Device();

        device.setId(deviceDto.getId());
        device.setValues(Set.copyOf(deviceDto.getValues()));
        device.setName(deviceDto.getName());

        return device;
    }
}

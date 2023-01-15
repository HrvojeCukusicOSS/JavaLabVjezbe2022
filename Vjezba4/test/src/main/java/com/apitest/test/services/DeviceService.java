package com.apitest.test.services;

import com.apitest.test.models.Device;
import com.apitest.test.repositories.DeviceRepository;
import com.apitest.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;
    public List<Device> createDevice(Device device){
        var user = userRepository.findById(device.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user"));
        device.setUser(user);
        deviceRepository.save(device);
        return deviceRepository.findAll();
    }

    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }

    public void deleteDevice(Long id){
        deviceRepository.deleteById(id);
    }

    public Device getDeviceById(Long id){
        return deviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid device"));
    }

    public Device updateDevice(Long id, Device device){
        var toUpdate = deviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid device"));
        toUpdate.setValue(device.getValue());
        toUpdate.setMeasurement_unit(device.getMeasurement_unit());
        var user = userRepository.findById(device.getUser().getId()).orElse(null);
        userRepository.save(user);
        toUpdate.setUser(user);
        return deviceRepository.save(toUpdate);
    }
}

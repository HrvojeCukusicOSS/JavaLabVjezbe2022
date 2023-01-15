package com.apitest.test.controllers;

import com.apitest.test.models.Device;
import com.apitest.test.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    /*@Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }*/

    @GetMapping("")
    public List<Device> getDevices() {
        return deviceService.getDevices();
    }

    @GetMapping("/{id}")
    public Device getDevice(@PathVariable("id") Long id) {
        return (deviceService.getDeviceById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable("id") Long id) {
        deviceService.deleteDevice(id);
    }

    @PostMapping("")
    public List<Device> createDevice(@RequestBody Device device) {
        return deviceService.createDevice(device);
    }

    @PutMapping("/device/{id}")
    public  Device updateDevice(@PathVariable Long id, @RequestBody Device device){
        return deviceService.updateDevice(id, device);
    }
}

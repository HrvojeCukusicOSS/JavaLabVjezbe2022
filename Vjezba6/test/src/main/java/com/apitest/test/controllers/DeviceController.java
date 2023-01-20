package com.apitest.test.controllers;

import com.apitest.test.dtos.DeviceDto;
import com.apitest.test.services.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/device")
@AllArgsConstructor
public class DeviceController {
    private DeviceService deviceService;

    @PostMapping("")
    public List<DeviceDto> createDevice(@RequestBody DeviceDto device){
        return deviceService.addDevice(device);
    }

    @GetMapping("")
    public List<DeviceDto> getAllDevices(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "field") String field, @RequestParam(name = "direction") String direction)
    {
        Page<DeviceDto> resultPage = deviceService.findPaginated(page, size, field, direction);

        return resultPage.getContent();
    }

    @PutMapping("/{id}")
    public DeviceDto updateDevice(@RequestBody DeviceDto device,@PathVariable Long id){
        return deviceService.updateDevice(device, id);
    }

    @DeleteMapping("/{id}")
    public List<DeviceDto> deleteDevice(@PathVariable Long id){
        return deviceService.deleteDevice(id);
    }

    @GetMapping("/{id}")
    public DeviceDto getValueByYear(@PathVariable Long id, @RequestParam(name="year", required = false) int year, @RequestParam(name="months", required = false) String months){
        if(months.equalsIgnoreCase("yes")){
            return deviceService.getMonthsInAYearValue(id, year);
        }
        else if(months.equalsIgnoreCase("no")) {
            return deviceService.getYearlyValue(id, year);
        }
        else {
            return deviceService.getValueByMonthInYear(id, year, months);
        }
    }
}

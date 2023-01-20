package com.frontend.test2.controllers;

import com.frontend.test2.service.FrontendDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/devices")
@AllArgsConstructor
public class FrontendDeviceController {

    private FrontendDeviceService frontendDeviceService;

    @GetMapping("")
    public String showDevices(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                              @RequestParam(name = "size", defaultValue = "2", required = false) int size,
                              @RequestParam(name = "field", required = false) String field,
                              @RequestParam(name = "direction", required = false) String direction, Model model) throws IOException, InterruptedException{
        model.addAttribute("deviceList", frontendDeviceService.getDevices(page, size, field, direction));
        return "devices";
    }

    @GetMapping("/form")
    String getForm(){
        return "form";
    }

    @PostMapping("/form")
    String postman(@RequestParam(name = "id") String id,
                   @RequestParam(name = "date") String date,
                   @RequestParam(name = "value") String value) throws IOException, InterruptedException {
        frontendDeviceService.AddData(id, date, value);
        return "redirect:/devices";
    }
}

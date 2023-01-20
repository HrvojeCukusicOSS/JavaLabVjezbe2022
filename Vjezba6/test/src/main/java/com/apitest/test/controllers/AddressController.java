package com.apitest.test.controllers;

import com.apitest.test.dtos.AddressDto;
import com.apitest.test.dtos.DeviceDto;
import com.apitest.test.models.Address;
import com.apitest.test.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("")
    public List<AddressDto> createAddress(@RequestBody AddressDto address){
        return addressService.addAddress(address);
    }

    @GetMapping("")
    public List<AddressDto> getAllAddressesPaged(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size, @RequestParam(name = "field") String field, @RequestParam(name = "direction") String direction)
    {
        Page<AddressDto> resultPage = addressService.findPaginated(page, size, field, direction);

        return resultPage.getContent();
    }

    @PutMapping("/{id}")
    public AddressDto updateAddress(@RequestBody AddressDto address,@PathVariable Long id){
        return addressService.updateAddress(address, id);
    }

    @DeleteMapping("/{id}")
    public List<AddressDto> deleteAddress(@PathVariable Long id){
        return addressService.deleteAddress(id);
    }
}

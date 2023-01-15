package com.apitest.test.controllers;

import com.apitest.test.models.Address;
import com.apitest.test.services.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("")
    public List<Address> createAddress(@RequestBody Address address){
        return addressService.addAddress(address);
    }

    @GetMapping("")
    public List<Address> getAllAddresses(){
        return addressService.readAllAddress();
    }

    @PutMapping("/{id}")
    public Address updateAddress(@RequestBody Address address,@PathVariable Long id){
        return addressService.updateAddress(address, id);
    }

    @DeleteMapping("/{id}")
    public List<Address> deleteAddress(@PathVariable Long id){
        return addressService.deleteAddress(id);
    }
}

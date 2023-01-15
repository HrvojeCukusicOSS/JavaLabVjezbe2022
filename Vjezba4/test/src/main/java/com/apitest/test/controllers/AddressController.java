package com.apitest.test.controllers;

import com.apitest.test.models.Address;
import com.apitest.test.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<Address> getAddresses() {
        return addressService.getAddresses();
    }

    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable("id") Long id) {
        return (addressService.getAddressById(id));
    }

    @DeleteMapping("/address/{id}")
    public void deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
    }

    @PostMapping("/address")
    public List<Address> createAddress(@RequestBody Address address) {
        return (addressService.createAddress(address));
    }

    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable Long id, @RequestBody Address address){
        return (addressService.updateAddress(id, address));
    }
}

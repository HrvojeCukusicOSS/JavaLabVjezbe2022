package com.apitest.test.services;

import com.apitest.test.models.Address;
import com.apitest.test.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;

    public List<Address> addAddress(Address address){
        addressRepository.save(address);
        return addressRepository.findAll();
    }

    public List<Address> readAllAddress(){
        return addressRepository.findAll();
    }

    public Address updateAddress(Address address, Long id){
        Address toUpdate = addressRepository.findById(id).orElse(null);
        toUpdate.setCity(address.getCity());
        toUpdate.setNumber(address.getNumber());
        toUpdate.setUsers(address.getUsers());
        addressRepository.save(toUpdate);
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> deleteAddress(Long id){
        addressRepository.deleteById(id);
        return addressRepository.findAll();
    }
}

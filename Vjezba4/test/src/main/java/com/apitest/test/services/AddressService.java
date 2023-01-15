package com.apitest.test.services;

import com.apitest.test.models.Address;
import com.apitest.test.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

     public List<Address> createAddress(Address address){
         addressRepository.save(address);
         return addressRepository.findAll();
     }

     public List<Address> getAddresses(){
         return addressRepository.findAll();
    }

    public void deleteAddress(Long id){
         addressRepository.deleteById(id);
    }

    public Address getAddressById(Long id){
         return addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid address"));
    }

    public Address updateAddress(Long id, Address address){
         var toUpdate = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid address"));
         toUpdate.setCity(address.getCity());
         toUpdate.setNumber(address.getNumber());
         return addressRepository.save(toUpdate);
    }
}

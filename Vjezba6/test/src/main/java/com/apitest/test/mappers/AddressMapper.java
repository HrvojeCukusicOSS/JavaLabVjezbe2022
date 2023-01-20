package com.apitest.test.mappers;

import com.apitest.test.dtos.AddressDto;
import com.apitest.test.models.Address;
import com.apitest.test.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    public AddressDto mapEntityToDto(Address address) {
        var addressDto = new AddressDto();

        addressDto.setId(address.getId());
        addressDto.setNumber(address.getNumber());
        addressDto.setCity(address.getCity());
        addressDto.setUsers(address.getUsers().stream().collect(Collectors.toList()));

        return addressDto;
    }

    public List<AddressDto> mapMultipleEntityToDto(List<Address> addresses) {
        if(addresses != null){
            List<AddressDto> toReturn = new ArrayList<>();
            for (var address:addresses) {
                var addressDto = new AddressDto();
                addressDto.setId(address.getId());
                addressDto.setNumber(address.getNumber());
                addressDto.setCity(address.getCity());
                addressDto.setUsers(address.getUsers().stream().collect(Collectors.toList()));
                toReturn.add(addressDto);
            }
            return toReturn;
        }
        return null;
    }

    public Address mapDtoToEntity(AddressDto addressDto) {
        var address = new Address();

        address.setId(addressDto.getId());
        address.setNumber(addressDto.getNumber());
        address.setCity(addressDto.getCity());
        address.setUsers((Set<User>) (addressDto.getUsers()));

        return address;
    }

    public Page<AddressDto> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<Address> source) {
        List<AddressDto> dtos = mapMultipleEntityToDto(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }
}

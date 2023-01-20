package com.apitest.test.services;

import com.apitest.test.dtos.AddressDto;
import com.apitest.test.dtos.DeviceDto;
import com.apitest.test.mappers.AddressMapper;
import com.apitest.test.models.Address;
import com.apitest.test.models.Device;
import com.apitest.test.repositories.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {
    private AddressRepository addressRepository;

    private AddressMapper addressMapper;

    public List<AddressDto> addAddress(AddressDto address){
        addressRepository.save(addressMapper.mapDtoToEntity(address));
        return addressMapper.mapMultipleEntityToDto((List<Address>) addressRepository.findAll());
    }

    public List<AddressDto> readAllAddress(){
        return addressMapper.mapMultipleEntityToDto((List<Address>) addressRepository.findAll());
    }

    public Page<AddressDto> findPaginated(int page, int size, String field, String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageRequest = createPageRequest(page, size, sort);

        Page<Address> searchResultPage = addressRepository.findAll(pageRequest);

        return addressMapper.mapEntityPageIntoDTOPage( pageRequest, searchResultPage);
    }

    private Pageable createPageRequest(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return pageable;
    }

    public AddressDto updateAddress(AddressDto address, Long id){
        Address toUpdate = addressRepository.findById(id).orElse(null);
        toUpdate.setCity(address.getCity());
        toUpdate.setNumber(address.getNumber());
        addressRepository.save(toUpdate);
        return addressMapper.mapEntityToDto(addressRepository.findById(id).orElse(null));
    }

    public List<AddressDto> deleteAddress(Long id){
        addressRepository.deleteById(id);
        return addressMapper.mapMultipleEntityToDto((List<Address>) addressRepository.findAll());
    }
}

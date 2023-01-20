package com.apitest.test.repositories;

import com.apitest.test.models.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

}

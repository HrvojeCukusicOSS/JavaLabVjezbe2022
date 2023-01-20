package com.apitest.test.repositories;

import com.apitest.test.models.Measurement;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long> {
    List<Measurement> findAllByDeviceId(Long id);
}

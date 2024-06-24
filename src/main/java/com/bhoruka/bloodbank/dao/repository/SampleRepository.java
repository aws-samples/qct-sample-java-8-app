package com.bhoruka.bloodbank.dao.repository;

import com.bhoruka.bloodbank.dao.entity.Sample;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SampleRepository extends PagingAndSortingRepository<Sample, String> {
}

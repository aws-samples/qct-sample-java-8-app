package com.bhoruka.bloodbank.dao.repository;

import com.bhoruka.bloodbank.dao.entity.Donor;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DonorRepository extends PagingAndSortingRepository<Donor, String> {
}

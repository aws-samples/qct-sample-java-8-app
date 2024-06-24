package com.bhoruka.bloodbank.dao.repository;

import com.bhoruka.bloodbank.dao.entity.Inventory;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface InventoryRepository extends PagingAndSortingRepository<Inventory, String> {
}

package com.bashir.repository;

import org.springframework.data.repository.CrudRepository;

import com.bashir.entity.BillEntity;

public interface BillRepository extends CrudRepository<BillEntity, Long> {
	
	public BillEntity findByBillId(Long billId);

}

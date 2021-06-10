package com.bashir.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bashir.entity.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long>{

	public EmployeeEntity findByEmployeeId(String employeeId);
	public List<EmployeeEntity> findByEmployeeType(String employeeType);
	public void deleteByEmployeeId(String employeeId);
	public EmployeeEntity findByEmailId(String emailId);
	
}

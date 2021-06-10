package com.bashir.data;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<EmployeeEntity, Long>{

	public EmployeeEntity findByEmailId(String emailId);
}

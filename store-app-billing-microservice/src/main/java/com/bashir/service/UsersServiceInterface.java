package com.bashir.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bashir.model.CreateEmployeeResponseModel;

@FeignClient(name="store-app-users-service", fallback=UsersFallback.class)
public interface UsersServiceInterface {
	
	@GetMapping(value="/salesman/{salesmanId}")
	public ResponseEntity<CreateEmployeeResponseModel> getSalesman(
			@PathVariable String salesmanId);

}


@Component
class UsersFallback implements UsersServiceInterface
{

	@Override
	public ResponseEntity<CreateEmployeeResponseModel> 
	        getSalesman(String salesmanId) {
		
		CreateEmployeeResponseModel employee = new CreateEmployeeResponseModel();
		employee.setFirstname("x");
		employee.setLastname("x");
		employee.setEmailId("x");
		employee.setEmployeeId("x");
		employee.setPhoneNumber("x");
		return new ResponseEntity<CreateEmployeeResponseModel>(employee, 
				HttpStatus.OK);
	}
	
}

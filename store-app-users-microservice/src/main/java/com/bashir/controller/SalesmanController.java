package com.bashir.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bashir.model.request.CreateEmployeeRequestModel;
import com.bashir.model.request.UpdateEmployeeRequestModel;
import com.bashir.model.response.CreateEmployeeResponseModel;
import com.bashir.model.response.UpdateEmployeeResponseModel;
import com.bashir.model.response.UpgradeEmployeeResponseModel;
import com.bashir.service.EmployeeService;
import com.bashir.shared.EmployeeDto;


@RestController
@RequestMapping("/salesman")
public class SalesmanController {

	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/status/check")
	public String checkStatus()
	{
		return "WORKING";
	}
	
	@PostMapping
	public ResponseEntity<CreateEmployeeResponseModel> addNewSalesman(
			@RequestBody CreateEmployeeRequestModel createSalesmanRequest)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto createSalesmanDto = 
				modelMapper.map(createSalesmanRequest, EmployeeDto.class);
		
		EmployeeDto createdSalesmanDto= 
				employeeService.createNewSalesman(createSalesmanDto);
		
		CreateEmployeeResponseModel createdSalesman = 
				modelMapper.map(createdSalesmanDto,
						CreateEmployeeResponseModel.class);
		
		return new ResponseEntity<CreateEmployeeResponseModel>
		                 (createdSalesman, HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public ResponseEntity<List<CreateEmployeeResponseModel>> getAllSalesman()
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
	    List<EmployeeDto> employeeDtoList = employeeService
	    		     .getSalesmanList();
	    
	    List<CreateEmployeeResponseModel> salesmanList = 
	    		new ArrayList<CreateEmployeeResponseModel>();
	    
	    for(EmployeeDto salesmanDto: employeeDtoList)
	    {
	    	CreateEmployeeResponseModel salesman = 
	    		modelMapper.map(salesmanDto, CreateEmployeeResponseModel.class);
	    	salesmanList.add(salesman);
	    	
	    }
	    
	    return new ResponseEntity<List<CreateEmployeeResponseModel>>(
	    		salesmanList, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{employeeId}")
	public ResponseEntity<CreateEmployeeResponseModel> getSalesman(
			@PathVariable String employeeId)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
				
		EmployeeDto employeeDto = null;
		try {
			employeeDto = employeeService
					      .getEmployee(employeeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CreateEmployeeResponseModel employee = modelMapper.map(employeeDto, 
				CreateEmployeeResponseModel.class);
		
		return new ResponseEntity<CreateEmployeeResponseModel>(employee,
				HttpStatus.OK);
		
		
	}
	
	@PutMapping(value="upgrade/{salesmanId}")
	public ResponseEntity<UpgradeEmployeeResponseModel> 
	upgradeSalesmanToManager(@PathVariable String salesmanId)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto employeeDto = employeeService
				.upgradeSalesmanToManager(salesmanId);
		
		UpgradeEmployeeResponseModel upgradedEmployee = modelMapper
				.map(employeeDto, UpgradeEmployeeResponseModel.class);
		
		return new ResponseEntity<UpgradeEmployeeResponseModel>(
				upgradedEmployee, HttpStatus.OK);
	}
	
	
	@PutMapping
	public ResponseEntity<UpdateEmployeeResponseModel> updateSalesman(
			@RequestBody UpdateEmployeeRequestModel updateSalesmanRequest)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto updateEmployeeDto = modelMapper.map(updateSalesmanRequest,
				                         EmployeeDto.class);
		
		EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(
				                         updateEmployeeDto);
	
	    UpdateEmployeeResponseModel updatedEmployee = modelMapper
	    		 .map(updatedEmployeeDto, UpdateEmployeeResponseModel.class);
	    
	    return new ResponseEntity<UpdateEmployeeResponseModel>(
	    		updatedEmployee, HttpStatus.OK);
	}



	@DeleteMapping("/{deleteSalesmanId}")
	public ResponseEntity<String> deleteSalesman(@PathVariable
			String deleteSalesmanId)
	{
		String deleteStatus = employeeService.deleteEmployee(deleteSalesmanId);
	    
		return new ResponseEntity<String>(deleteStatus, HttpStatus.OK);
	}

}

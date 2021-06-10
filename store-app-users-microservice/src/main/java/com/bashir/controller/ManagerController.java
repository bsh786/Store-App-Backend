package com.bashir.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
@RequestMapping("/manager")
public class ManagerController {
	
	
	@Autowired
	EmployeeService userService;
	
	@Autowired
	Environment env;
	
	
	
	@GetMapping("/status/check")
	public String checkStatus()
	{
		return "WORKING, CHANGING-VALUE: "+env.getProperty("changing.value");
	}
	
	@PostMapping
	public ResponseEntity<CreateEmployeeResponseModel>
	              addStoreManager(@RequestBody 
	            		  CreateEmployeeRequestModel newManagerRequest)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto newManagerDto = modelMapper
				.map(newManagerRequest, EmployeeDto.class);
		
		EmployeeDto createdManagerDto =
		    userService.createNewStoreManager(newManagerDto);
		
		CreateEmployeeResponseModel createdManager = 
				modelMapper.map(createdManagerDto, 
						CreateEmployeeResponseModel.class);
		
		return new ResponseEntity<CreateEmployeeResponseModel>
		         (createdManager, HttpStatus.CREATED);
		
	 }
	
	
	@GetMapping
	public ResponseEntity<List<CreateEmployeeResponseModel>> getAllManagers()
	{
		
		List<EmployeeDto> managersDtoList = userService
				.getStoreManagersList();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<CreateEmployeeResponseModel> managersList =
				new ArrayList<CreateEmployeeResponseModel>();
		
		for(EmployeeDto managerDto: managersDtoList)
		{
			CreateEmployeeResponseModel manager
			           = modelMapper.map(managerDto, 
			        		   CreateEmployeeResponseModel.class);
			
			managersList.add(manager);
		}
		
		return new ResponseEntity<List<CreateEmployeeResponseModel>>(
				managersList, HttpStatus.OK);
	}
	
	
	@GetMapping(value="/{userId}")
	public ResponseEntity<CreateEmployeeResponseModel> getManager(
			@PathVariable String userId)
	{
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto employeeDto = null;
		try {
			employeeDto = userService.getEmployee(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateEmployeeResponseModel employee = modelMapper.map(employeeDto,
				CreateEmployeeResponseModel.class);
		
		return new ResponseEntity<CreateEmployeeResponseModel>(employee,
				HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEmployeeResponseModel> updateManagerDetails(
			@RequestBody UpdateEmployeeRequestModel updateRequestModel)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto updateEmployeeDto = modelMapper.map(
				updateRequestModel, EmployeeDto.class);
		
		EmployeeDto updatedEmployeeDto = 
				userService.updateEmployee(updateEmployeeDto);
		
		UpdateEmployeeResponseModel updatedEmployee = modelMapper
				.map(updatedEmployeeDto, UpdateEmployeeResponseModel.class);
		
		return new ResponseEntity<UpdateEmployeeResponseModel>(updatedEmployee,
				               HttpStatus.OK);
	}
	
	
	@PutMapping("/upgrade/{managerId}")
	public ResponseEntity<UpgradeEmployeeResponseModel> 
	        upgradeManagerToAdmin(@PathVariable String managerId)
	{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeDto employeeDto = userService.upgradeManagerToAdmin(managerId);
		UpgradeEmployeeResponseModel upgradedEmployee = modelMapper
				.map(employeeDto, UpgradeEmployeeResponseModel.class);
		
		return new ResponseEntity<UpgradeEmployeeResponseModel>(upgradedEmployee,
				HttpStatus.OK);
				
		
	}
	
		
	
	
	
	@DeleteMapping("/{deleteManagerId}")
	public ResponseEntity<String> deleteManager(@PathVariable
			String deleteManagerId)
	{
		String deleteStatus = userService.deleteEmployee(deleteManagerId);
	    
		return new ResponseEntity<String>(deleteStatus, HttpStatus.OK);
	}
	
	
}

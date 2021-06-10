package com.bashir.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bashir.entity.EmployeeEntity;
import com.bashir.repository.EmployeeRepository;
import com.bashir.shared.EmployeeDto;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	
	EmployeeRepository employeeRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.employeeRepository = employeeRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	
	@Override
	public EmployeeDto createNewStoreManager(
			EmployeeDto newStoreMangerDto) {
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().
		setMatchingStrategy(MatchingStrategies.STRICT);
		newStoreMangerDto.setEncryptedPassword(
				bCryptPasswordEncoder.encode(
				newStoreMangerDto.getPassword()));
		
		EmployeeEntity storeManager = modelMapper.map(
				newStoreMangerDto, EmployeeEntity.class);
		
		
		storeManager.setEmployeeType("Store Manager");
		
		
		employeeRepository.save(storeManager);
		
		EmployeeEntity createdEmployee = employeeRepository.
				findByEmployeeId(storeManager.getEmployeeId());
		
		
		
		EmployeeDto createdManager = modelMapper.map
				(createdEmployee, EmployeeDto.class);
				
		return createdManager;
	}



	@Override
	public EmployeeDto createNewSalesman(EmployeeDto newSalesman) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		   .setMatchingStrategy(MatchingStrategies.STRICT);
        
		newSalesman.setEncryptedPassword(bCryptPasswordEncoder.encode(
				newSalesman.getPassword()));
		
		EmployeeEntity salesman = modelMapper.map(newSalesman, 
				                      EmployeeEntity.class);
		
		salesman.setEmployeeType("Salesman");
		
		employeeRepository.save(salesman);
		
		EmployeeEntity createdSalesman = employeeRepository
				                  .findByEmployeeId(salesman.getEmployeeId());
		
		EmployeeDto createdSalesmanDto = modelMapper
				.map(createdSalesman, EmployeeDto.class);
		
		return createdSalesmanDto;
	}



	@Override
	public List<EmployeeDto> getStoreManagersList() {
		
		List<EmployeeEntity> managersList = employeeRepository
				.findByEmployeeType("Store Manager");
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<EmployeeDto> employeeDtoList = new
				ArrayList<EmployeeDto>();
		
		for(EmployeeEntity manager : managersList)
		{
			EmployeeDto employeeDto = modelMapper
					.map(manager, EmployeeDto.class);
			
			employeeDtoList.add(employeeDto);
		}
		
		
		return employeeDtoList;
	}



	@Override
	public List<EmployeeDto> getSalesmanList() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		List<EmployeeEntity> employeeList = employeeRepository
				       .findByEmployeeType("Salesman");
		
		List<EmployeeDto> employeeDtoList = new 
				ArrayList<EmployeeDto>();
		
		for(EmployeeEntity employee: employeeList)
		{
			EmployeeDto employeeDto = modelMapper
					.map(employee, EmployeeDto.class);
			
			employeeDtoList.add(employeeDto);
		}
		
		
		
		return employeeDtoList;
	}



	@Override
	public EmployeeDto getEmployee(String userId) throws Exception {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeEntity employee = employeeRepository.findByEmployeeId(userId);
		
		
		if(employee == null) throw new Exception("Incorrect employee ID");
		
		EmployeeDto employeeDto = modelMapper.map(employee,
				EmployeeDto.class);
		
		return employeeDto;
	}



	@Override
	public EmployeeDto updateEmployee(EmployeeDto updateEmployeeDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeEntity employee = employeeRepository.findByEmployeeId(
				updateEmployeeDetails.getEmployeeId());
		
		employee.setEmailId(updateEmployeeDetails.getEmailId());
		employee.setAddress(updateEmployeeDetails.getAddress());
		employee.setPhoneNumber(updateEmployeeDetails.getPhoneNumber());
		
		employeeRepository.save(employee);
		
		employee = employeeRepository.findByEmployeeId(updateEmployeeDetails
				.getEmployeeId());
		
		EmployeeDto updatedEmployeeDto = modelMapper.map(employee,
				EmployeeDto.class);
		
		return updatedEmployeeDto;
		
	}



	@Override
	public String deleteEmployee(String employeeId) {

       employeeRepository.deleteByEmployeeId(employeeId);
       
       return "Deleted";
		
	}



	@Override
	public EmployeeDto upgradeManagerToAdmin(String managerId) {
		
		ModelMapper modelMapper =  new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeEntity employee = employeeRepository.findByEmployeeId(managerId);
		employee.setEmployeeType("Admin");
		employee = employeeRepository.save(employee);
		
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		
		return employeeDto;
	}



	@Override
	public EmployeeDto upgradeSalesmanToManager(String salesmanId) {
		
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies
				.STRICT);
		
		EmployeeEntity employee = employeeRepository
				                  .findByEmployeeId(salesmanId);
		
		employee.setEmployeeType("Store Manager");
		
		employee = employeeRepository.save(employee);
        		
		EmployeeDto employeeDto = modelMapper.map(employee, 
				EmployeeDto.class);
		
		return employeeDto;
	}

	
	

}

package com.bashir.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bashir.data.EmployeeEntity;
import com.bashir.data.UsersRepository;
import com.bashir.shared.EmployeeDto; 

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	UsersRepository usersRespository;
	
	@Autowired
	public AuthenticationServiceImpl(UsersRepository usersRepository) {
		
		this.usersRespository = usersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		EmployeeEntity employee = usersRespository.findByEmailId(username);
		
		if(employee == null)
		{
			throw new UsernameNotFoundException("Invalid Email ID");
		}
		return new User(employee.getEmailId(),
				employee.getEncryptedPassword(), 
				true, true, true, true, new ArrayList<>());
	}

	@Override
	public EmployeeDto getEmployeeDetailsByEmail(String emailId) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		
		EmployeeEntity employee = usersRespository.findByEmailId(emailId);
		
		if(employee == null)
		{
			throw new UsernameNotFoundException("Invalid Email Id");
		}
		
		EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
		
		return employeeDto;
	}

}

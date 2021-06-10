package com.bashir.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bashir.shared.EmployeeDto;

public interface AuthenticationService extends UserDetailsService {

	public EmployeeDto getEmployeeDetailsByEmail(String emailId);
}

package com.bashir.service;

import java.util.List;

import com.bashir.shared.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto createNewStoreManager(EmployeeDto newStoreMangerDto);
    public EmployeeDto createNewSalesman(EmployeeDto newSalesman);
    public List<EmployeeDto> getStoreManagersList();
    public List<EmployeeDto> getSalesmanList();
    public EmployeeDto getEmployee(String userId) throws Exception;
    public EmployeeDto updateEmployee(EmployeeDto updateEmployeeDetails);
    public String deleteEmployee(String employeeId);
    public EmployeeDto upgradeManagerToAdmin(String managerId);
    public EmployeeDto upgradeSalesmanToManager(String salesmanId);
    
    
}

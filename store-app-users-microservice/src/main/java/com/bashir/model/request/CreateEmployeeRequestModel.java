package com.bashir.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateEmployeeRequestModel {
	
	@NotNull(message="Employee ID is mandatory to create new store manager")
	String employeeId;
	
	@NotNull(message="First name cannot be blank")
	String firstname;
	
	@NotNull(message="Last name cannot be blank")
	String lastname;
	
	@NotNull(message="Phone number cannot be empty")
	@Size(min=10, max=10, message="Phone number should be of 10 digits")
	String phoneNumber;
	
	@Email(message="Enter a valid email")
	@NotNull(message="Email cannot be null")
	String emailId;
	
	@NotNull(message="Age cannot be empty")
	int age;
	
	@NotNull(message="Address cannot be empty")
	String address;
	
	@NotNull(message="Password cannot be empty")
	@Size(min=10, max=16, message="Password should be between 10 to 16 characters")
	String password;
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}

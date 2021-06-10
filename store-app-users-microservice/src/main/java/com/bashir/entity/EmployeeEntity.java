package com.bashir.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class EmployeeEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(nullable=false, unique=true, length=50)
	private String employeeId;
	
	@Column(nullable=false, length=50)
	private String firstname;
	
	@Column(nullable=false, length=50)
	private String lastname;
	
	@Column(nullable=false, unique=true, length=50)
	private String phoneNumber;
	
	@Column(nullable=false, unique=true, length=100)
	private String emailId;
	
	@Column(nullable=false)
	private Integer age;
	
	@Column(nullable=false,length=300)
	private String address;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column(nullable=false)
	private String employeeType;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Integer getAge() {
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
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	
	public EmployeeEntity(long id, String employeeId, String firstname, String lastname, String phoneNumber,
			String emailId, int age, String address, String encryptedPassword, String employeeType) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.age = age;
		this.address = address;
		this.encryptedPassword = encryptedPassword;
		this.employeeType = employeeType;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}

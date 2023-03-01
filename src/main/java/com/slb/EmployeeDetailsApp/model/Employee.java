package com.slb.EmployeeDetailsApp.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	
	public int id;
	public String firstName;
	public String lastName;
	public float salary;
	public int addressId;
	public String address;
//	public Employee() {
//		super();
//	}
//	public Employee(String firstName, String lastName, float salary, int addressId, String address) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.salary = salary;
//		this.addressId = addressId;
//		this.address = address;
//	}
//	public Employee(int id, String firstName, String lastName, float salary, int addressId,String address) {
//		super();
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.salary = salary;
//		this.addressId = addressId;
//		this.address=address;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public float getSalary() {
//		return salary;
//	}
//	public void setSalary(float salary) {
//		this.salary = salary;
//	}
//	public int getAddressId() {
//		return addressId;
//	}
//	public void setAddressId(int addressId) {
//		this.addressId = addressId;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}

}

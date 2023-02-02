package com.slb.EmployeeDetailsApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Address1 {

	@Id
	public int addressId;
	public String address;
	
	public Address1(int addressId, String address) {
		super();
		this.addressId = addressId;
		this.address = address;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

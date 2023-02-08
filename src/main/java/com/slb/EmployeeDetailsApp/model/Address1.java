package com.slb.EmployeeDetailsApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address1 {

	@Id
	public int addressId;
	public String address;
	
	
//	public Address1() {
//		super();
//	}
//	public Address1(int addressId, String address) {
//		super();
//		this.addressId = addressId;
//		this.address = address;
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
//	
//	
}

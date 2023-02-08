package com.slb.EmployeeDetailsApp.model;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="Employee1")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private float salary;
	
	@ManyToOne // (cascade = CascadeType.MERGE)
	@JoinColumn(name="ad_id",referencedColumnName="addressId")
	private Address1 address;
	
	
	
//	public EmployeeEntity() {
//		super();
//	}
//	public EmployeeEntity(String firstName, String lastName, float salary, Address1 address) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.salary = salary;
//		this.address = address;
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
//	public Address1 getAddress() {
//		return address;
//	}
//	public void setAddress(Address1 address) {
//		this.address = address;
//	}
	
	
}

package com.slb.EmployeeDetailsApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slb.EmployeeDetailsApp.Repo.AddressRepo;
import com.slb.EmployeeDetailsApp.Repo.EmployeeRepo;
import com.slb.EmployeeDetailsApp.exception.EmployeesAlreadyExistsException;
import com.slb.EmployeeDetailsApp.exception.NoSuchEmployeeExistsException;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	public EmployeeEntity createEmpRecord(EmployeeEntity emp) {
		EmployeeEntity employee=employeeRepo.findById(emp.getId());
		if(employee!=null) {
			throw new EmployeesAlreadyExistsException(
	                "Employee already exists!!");
		}
		else {
			if(!addressRepo.existsById(emp.getAddress().getAddressId())) {
				addressRepo.save(emp.getAddress());
			}
			return employeeRepo.save(emp);
		}
	}
	public List<EmployeeEntity> selectEmpRecords(){
		return employeeRepo.findAll();
		
	}
	public EmployeeEntity selectEmp(int empId) {
		EmployeeEntity emp=employeeRepo.findById(empId);
		 if (emp == null)
	            throw new NoSuchEmployeeExistsException(
	                "No Such Employee exists!!");
		return emp;
		
	}
	public EmployeeEntity updateEmp(EmployeeEntity emp) {
		EmployeeEntity employee=employeeRepo.findById(emp.getId());
		if(employee==null) {
			throw new NoSuchEmployeeExistsException(
	                "No Such Employee exists!!");
		}
		else {
			if(!addressRepo.existsById(emp.getAddress().getAddressId())) {
				addressRepo.save(emp.getAddress());
			}
			return employeeRepo.save(emp);
		}
	}
	public EmployeeEntity deleteEmpRecord(int empId) {
		EmployeeEntity emp=employeeRepo.findById(empId);
		 if (emp == null)
	            throw new NoSuchEmployeeExistsException(
	                "No Such Employee exists!!");
		 else
			return employeeRepo.deleteById(empId);
	}

	public List<EmployeeEntity>selectEmpByName(String firstName) {
		return employeeRepo.findByFirstName(firstName);
		
	}

}

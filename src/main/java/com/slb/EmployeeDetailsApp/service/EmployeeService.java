package com.slb.EmployeeDetailsApp.service;

import java.util.List;

import com.slb.EmployeeDetailsApp.model.EmployeeEntity;

public interface EmployeeService {

	EmployeeEntity createEmpRecord(EmployeeEntity emp);
	List<EmployeeEntity> selectEmpRecords();
	EmployeeEntity selectEmp(int empId);
	EmployeeEntity updateEmp(EmployeeEntity emp);
	EmployeeEntity deleteEmpRecord(int empId);
	List<EmployeeEntity>selectEmpByName(String firstName);
}

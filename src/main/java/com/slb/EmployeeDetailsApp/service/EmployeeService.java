package com.slb.EmployeeDetailsApp.service;

import java.util.List;

import com.slb.EmployeeDetailsApp.model.EmployeeEntity;

public interface EmployeeService {

	void createEmpRecord(EmployeeEntity emp);
	List<EmployeeEntity> selectEmpRecords();
	EmployeeEntity selectEmp(int empId);
	void updateEmp(EmployeeEntity emp);
	void deleteEmpRecord(int empId);
	List<EmployeeEntity>selectEmpByName(String firstName);
}

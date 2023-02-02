package com.slb.EmployeeDetailsApp.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slb.EmployeeDetailsApp.model.Employee1;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee1,Integer>{

}

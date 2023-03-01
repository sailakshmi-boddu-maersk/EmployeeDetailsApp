package com.slb.EmployeeDetailsApp.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slb.EmployeeDetailsApp.model.EmployeeEntity;


@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Integer>{

	List<EmployeeEntity> findByFirstName(String firstName);
    
	EmployeeEntity findById(int id);
	EmployeeEntity deleteById(int id);
}

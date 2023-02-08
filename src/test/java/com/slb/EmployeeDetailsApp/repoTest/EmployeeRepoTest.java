package com.slb.EmployeeDetailsApp.repoTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.slb.EmployeeDetailsApp.Repo.AddressRepo;
import com.slb.EmployeeDetailsApp.Repo.EmployeeRepo;
import com.slb.EmployeeDetailsApp.model.Address1;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;

//@SpringBootTest
@DataJpaTest
public class EmployeeRepoTest {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	AddressRepo addressRepo;
	
	@Test
	@Order(1)
	@Rollback(false)
	public void saveTest() {
		Address1 add=new Address1(102,"Pune");
		EmployeeEntity emp=EmployeeEntity.builder().id(1).firstName("employee1").lastName("abc").salary(20000).address(add).build();
		addressRepo.save(add);
		EmployeeEntity existingEmp=employeeRepo.save(emp);
		assertNotNull(existingEmp);
//		assertEquals(emp,employeeRepo.save(emp));
		System.out.println("1 save test");
	}
	
	@Test
	@Order(2)
	@Rollback(false)
	public void findByIdTest() {
		EmployeeEntity existingEmp=employeeRepo.findById(1);
		assertNotNull(existingEmp);
		System.out.println("2 find test");
	}
	
	@Test
	@Order(3)
	@Rollback(false)
	public void findAll() {
		List <EmployeeEntity> empList =employeeRepo.findAll();
		assertEquals(1,empList.size());
		
	}
	
	@Test
	@Order(4)
	@Rollback(false)
	public void findByFirstName() {
		List <EmployeeEntity> empList =employeeRepo.findByFirstName("employee1");
		assertEquals(1,empList.size());
	}
	
	@Test
	@Order(5)
	public void delete() {
		boolean isPresent1=employeeRepo.existsById(1);
		employeeRepo.deleteById(1);
		boolean isPresent2=employeeRepo.existsById(1);
		assertEquals(true,isPresent1);
		assertEquals(false,isPresent2);
	}
}

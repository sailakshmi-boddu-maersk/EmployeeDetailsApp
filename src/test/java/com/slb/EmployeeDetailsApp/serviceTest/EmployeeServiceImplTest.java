package com.slb.EmployeeDetailsApp.serviceTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.slb.EmployeeDetailsApp.Repo.AddressRepo;
import com.slb.EmployeeDetailsApp.Repo.EmployeeRepo;
import com.slb.EmployeeDetailsApp.exception.EmployeesAlreadyExistsException;
import com.slb.EmployeeDetailsApp.exception.NoSuchEmployeeExistsException;
import com.slb.EmployeeDetailsApp.model.Address1;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;
import com.slb.EmployeeDetailsApp.service.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepo employeeRepo;
	
	@Mock
	AddressRepo addressRepo;
	
	@Nested //to test createEmpRecords method
	class  createEmpRecordTests {
		
		@Test // when address record already exists
		public void  createEmpRecordTest1() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(null);
			when(addressRepo.existsById(101)).thenReturn(true);
			when(employeeRepo.save(Mockito.any(EmployeeEntity.class))).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.createEmpRecord(emp));
			verify(employeeRepo).findById(1);
			verify(addressRepo).existsById(101);
			verify(employeeRepo).save(Mockito.any(EmployeeEntity.class));
			
		}
		@Test //when address record not exists
		public void  createEmpRecordTest2() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(null);
			when(addressRepo.existsById(101)).thenReturn(false);
			when(addressRepo.save(Mockito.any(Address1.class))).thenReturn(new Address1(101,"address1"));
			when(employeeRepo.save(Mockito.any(EmployeeEntity.class))).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.createEmpRecord(emp));
			verify(employeeRepo).findById(1);
			verify(addressRepo).existsById(101);
			verify(addressRepo).save(Mockito.any(Address1.class));
			verify(employeeRepo).save(Mockito.any(EmployeeEntity.class));
			
		}
		@Test //when employee already exists exception thrown
		public void  createEmpRecordTest3() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(emp);
			 assertThrows(EmployeesAlreadyExistsException.class, () -> {
	    	    	employeeServiceImpl.createEmpRecord(emp);
	    	    });
			 verify(employeeRepo).findById(1);
			
		}
		
	}
	
	@Nested //to test selectEmpRecords method
	class selectEmpRecordsTests{
		
		@Test //to verify employee list is returned successfully
		public void selectEmpRecordsTest1() {
			List<EmployeeEntity> empList=new ArrayList<>();
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			empList.add(emp);
			empList.add(new EmployeeEntity(2,"Employee1","xyz",20000.0F,new Address1(101,"address2")));
			when(employeeRepo.findAll()).thenReturn(empList);
			assertEquals(empList,employeeServiceImpl.selectEmpRecords());
			verify(employeeRepo).findAll();
		}
	}
	
	@Nested //to test selectEmp method
	class selectEmpTests{
		
		@Test //when employee is found
		public void selectEmpTest1() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.selectEmp(1));
			verify(employeeRepo).findById(1);
		}
		
		@Test //when employee is not found
		public void selectEmpTest2() {
			when(employeeRepo.findById(1)).thenReturn(null);
			assertThrows(NoSuchEmployeeExistsException.class, () -> {
    	    	employeeServiceImpl.selectEmp(1);
    	    });
			verify(employeeRepo).findById(1);
		}
	}
	
	@Nested //to test updateEmp method
	class updateEmpTests{
		
		@Test //when address already exists
		public void updateEmpTest1() {
			
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(emp);
			when(addressRepo.existsById(101)).thenReturn(true);
			when(employeeRepo.save(Mockito.any(EmployeeEntity.class))).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.updateEmp(emp));
			verify(employeeRepo).findById(1);
			verify(addressRepo).existsById(101);
			verify(employeeRepo).save(Mockito.any(EmployeeEntity.class));
			
		}
		
		@Test //when address not exists
		public void updateEmpTest2() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(new EmployeeEntity());
			when(addressRepo.existsById(101)).thenReturn(false);
			when(addressRepo.save(Mockito.any(Address1.class))).thenReturn(new Address1(101,"address1"));
			when(employeeRepo.save(Mockito.any(EmployeeEntity.class))).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.updateEmp(emp));
			verify(employeeRepo).findById(1);
			verify(addressRepo).existsById(101);
			verify(addressRepo).save(Mockito.any(Address1.class));
			verify(employeeRepo).save(Mockito.any(EmployeeEntity.class));
		}
		
		@Test //when employee not exists exception thrown
		public void  updateEmpTest3() {
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(null);
			 assertThrows(NoSuchEmployeeExistsException.class, () -> {
	    	    	employeeServiceImpl.updateEmp(emp);
	    	    });
			 verify(employeeRepo).findById(1);
			
		}
		
	}
	
	@Nested //to test deleteEmpRecords method
	class deleteEmpRecordTests{
		
		@Test //when employee record not found 
		public void deleteEmpRecordTest1() {
			when(employeeRepo.findById(1)).thenReturn(null);
			assertThrows(NoSuchEmployeeExistsException.class, () -> {
    	    	employeeServiceImpl.deleteEmpRecord(1);
    	    });
		 verify(employeeRepo).findById(1);	
			
		}
		
		@Test //when employee record not found 
		public void deleteEmpRecordTest2() {
			
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			when(employeeRepo.findById(1)).thenReturn(emp);
			when(employeeRepo.deleteById(1)).thenReturn(emp);
			assertEquals(emp,employeeServiceImpl.deleteEmpRecord(1));
			verify(employeeRepo).findById(1);
			verify(employeeRepo).deleteById(1);
		}
	}
	@Nested //to test slectEmpByName method
	class selectEmpByNameTests{
		
		@Test //to verify the employee list returned
		public void selectEmpByNameTest1() {
			List<EmployeeEntity> empList=new ArrayList<>();
			EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
			empList.add(emp);
			empList.add(new EmployeeEntity(2,"Employee1","xyz",20000.0F,new Address1(101,"address2")));
			when(employeeRepo.findByFirstName("Employee1")).thenReturn(empList);
			assertEquals(empList,employeeServiceImpl.selectEmpByName("Employee1"));
			verify(employeeRepo).findByFirstName("Employee1");
		}
	}
	
	 
}

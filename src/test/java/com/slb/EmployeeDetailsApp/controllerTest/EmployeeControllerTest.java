package com.slb.EmployeeDetailsApp.controllerTest;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.slb.EmployeeDetailsApp.controller.EmployeeController;
import com.slb.EmployeeDetailsApp.exception.EmployeesAlreadyExistsException;
import com.slb.EmployeeDetailsApp.exception.NoSuchEmployeeExistsException;
import com.slb.EmployeeDetailsApp.model.Address1;
import com.slb.EmployeeDetailsApp.model.Employee;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;
import com.slb.EmployeeDetailsApp.service.EmployeeServiceImpl;

import static org.mockito.Mockito.when;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	
	 @Autowired
	  private MockMvc mockMvc;
	 
//	 @InjectMocks
//	 EmployeeController employeeController;

	 @MockBean
	 EmployeeServiceImpl employeeServiceImpl;
	 
	 @Autowired
	 WebApplicationContext wac;

	 @BeforeEach
	 void setUp() {
		 mockMvc=MockMvcBuilders
				 .webAppContextSetup(wac)
//				 .apply(springSecurity())
				 .build();
	 }
	 
	 @WithMockUser("spring")
	 @Test
	 public void indexPageTest() throws Exception {
			mockMvc.perform(get("/"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("index"));
		}
	 
	 @WithMockUser("spring")
	 @Test  //to check new request handling
		public void newFormTest() throws Exception {
			mockMvc.perform(get("/new"))
	        .andExpect(status().isOk())
	        .andExpect(view().name("employee-form"));
		}
	 
	 @Nested  //to check insert request handling 
		class insertTests{
		 
			@Test  //when no exception is thrown
			@WithMockUser("spring")
			public void insertTest1() throws Exception {
				when(employeeServiceImpl.createEmpRecord(Mockito.any(EmployeeEntity.class))).thenReturn(new EmployeeEntity());
				mockMvc.perform(post("/insert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)		
				.param("id","1")
				.param("firstName","Employee1")
				.param("lastName","abc")
				.param("salary", "20000")
				.param("addressId","101")
				.param("address","address1"))
//		        .andExpect(status().isMovedTemporarily())
		        .andExpect(status().is3xxRedirection())
		        .andExpect(redirectedUrl("/list"));
				verify(employeeServiceImpl).createEmpRecord(Mockito.any(EmployeeEntity.class));
			}
			
		    @WithMockUser("spring")
			@Test  //when invalid details where filled in the form
			public void insertTest2() throws Exception {
				mockMvc.perform(post("/insert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)		
				.param("id","abc")
				.param("firstName","Employee1")
				.param("lastName","abc")
				.param("salary", "20000")
				.param("addressId","101")
				.param("address","address1"))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("error"));
		        
			}
			
		    @WithMockUser("spring")
			@Test  //while no request parameters are passed
			public void insertTest3() throws Exception {
				mockMvc.perform(post("/insert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)		
				.param("id","")
				.param("firstName","")
				.param("lastName","")
				.param("salary", "")
				.param("addressId","")
				.param("address",""))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("error"));
			}
		    
		    @WithMockUser("spring")
			@Test  //when the employee with id already exists
			public void insertTest4() throws Exception {
		    	when(employeeServiceImpl.createEmpRecord(Mockito.any(EmployeeEntity.class))).thenThrow(EmployeesAlreadyExistsException.class);
				mockMvc.perform(post("/insert")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)		
				.param("id","12")
				.param("firstName","Employee1")
				.param("lastName","abc")
				.param("salary", "20000")
				.param("addressId","101")
				.param("address","address1"))
				.andExpect(status().isConflict())
				.andExpect(view().name("error"));
				verify(employeeServiceImpl).createEmpRecord(Mockito.any(EmployeeEntity.class));
		        
			}
		}
	 @Nested   //to check edit request handling 
		class editTests{
			
			@Test     //when request handled without any exception
			public void editTest1() throws Exception {
				EmployeeEntity emp =new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
				when(employeeServiceImpl.selectEmp(1)).thenReturn(emp);
				 mockMvc.perform(get("/edit")
			                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
			                .param("id","1")
			                .sessionAttr("emp",emp))
				           .andExpect(status().isOk())
			               .andExpect(view().name("employee-form"));
				 
				 verify(employeeServiceImpl).selectEmp(1);		                
			}
			
			@Test  //while passing incorrect form data
			public void editTest2() throws Exception {
				Employee emp=new Employee(1,"Employee1","abc",20000.0F,101,"address1");
				mockMvc.perform(get("/edit")
		                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		                .param("id","abc")
		                .sessionAttr("emp",emp))
				        .andExpect(status().isBadRequest())
				        .andExpect(view().name("error"));
			}
			
			@Test  //while no request parameters are passed
			public void editTest3() throws Exception {
				Employee emp=new Employee(1,"Employee1","abc",20000.0F,101,"address1");
				mockMvc.perform(get("/edit")
		                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		                .sessionAttr("emp",emp))
				        .andExpect(status().isBadRequest())
				        .andExpect(view().name("error"));
			}
		}
	 @Nested  //to check delete request handling 
	 class updateTests{
		 @Test   //when request handled without any exception
		 public void updateTest1() throws Exception {
			 when(employeeServiceImpl.updateEmp(Mockito.any(EmployeeEntity.class))).thenReturn(new EmployeeEntity());
			 mockMvc.perform(put("/update"))
			 .andExpect(status().isMovedTemporarily())
			 .andExpect(redirectedUrl("/list"));
			 verify(employeeServiceImpl).updateEmp(Mockito.any(EmployeeEntity.class));
		}
		 
		 @Test   //to check edit request handling 
		 public void updateTest2() throws Exception {
			 when(employeeServiceImpl.updateEmp(Mockito.any(EmployeeEntity.class))).thenThrow(NoSuchEmployeeExistsException.class);
			 mockMvc.perform(put("/update")
					.param("id","1")
					.param("firstName","Employee1")
					.param("lastName","abc")
					.param("salary", "20000")
					.param("addressId","101")
					.param("address","address1"))
			 .andExpect(status().isNotFound())
			 .andExpect(view().name("error")); 
			 verify(employeeServiceImpl).updateEmp(Mockito.any(EmployeeEntity.class));
		 }
	 }
		
	 @Nested  //to check delete request handling 
		class deleteTests{
			@Test   //when request handled without any exception
			public void deleteTest1() throws Exception {
				when(employeeServiceImpl.deleteEmpRecord(1)).thenReturn(new EmployeeEntity());
				mockMvc.perform(delete("/delete")
		                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		                .param("id","1"))
						.andExpect(status().isMovedTemporarily())
						.andExpect(redirectedUrl("/list"));
			 
			 verify(employeeServiceImpl).deleteEmpRecord(1);
			}
			
			@Test  //while passing incorrect data
			public void deleteTest2() throws Exception {
				when(employeeServiceImpl.deleteEmpRecord(127)).thenThrow(NoSuchEmployeeExistsException.class);
				mockMvc.perform(delete("/delete")
		                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
		                .param("id","127"))
						.andExpect(status().isNotFound())
						.andExpect(model().attribute("msg",is("No such Employee Found")))
						.andExpect(view().name("error")); 
				verify(employeeServiceImpl).deleteEmpRecord(127);
			}
			
			@Test //while no request parameters are passed
			public void deleteTest3() throws Exception {
				mockMvc.perform(delete("/delete"))
						.andExpect(status().isBadRequest())
						.andExpect(view().name("error"));
			}
		}
	 @Nested //to check empRec request handling 
		class empRecTests{
			@Test  //when request handled without any exception
			public void empRecTest1() throws Exception {
				mockMvc.perform(get("/empRec")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("action", "edit"))
		        .andExpect(status().isOk())
		        .andExpect(view().name("employeeName"));
			}
			
			@Test  //while passing incorrect form data
			public void empRecTest2() throws Exception {
				mockMvc.perform(get("/empRec")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("error"));
			}
			
			@Test  //while no request parameters are passed
			public void empRecTest3() throws Exception {
				mockMvc.perform(get("/empRec"))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("error"));
			}
		}
	 @Nested  //to check getEmpByName request handling 
		class getEmpByNameTests{
			
			@Test   //when request handled without any exception
			public void getEmpByNameTest1() throws Exception {
				List<EmployeeEntity> empList=new ArrayList<>();
				EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
				empList.add(emp);
				empList.add(new EmployeeEntity(2,"Employee1","xyz",20000.0F,new Address1(101,"address2")));
				when(employeeServiceImpl.selectEmpByName("Employee1")).thenReturn(empList);
				mockMvc.perform(get("/getEmps")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("firstName", "Employee1")
						.param("action", "edit"))
				        .andExpect(status().isOk())
				        .andExpect(view().name("employeeListByName"))
				        .andExpect(model().attribute("listEmployeeByName", hasSize(2)))
		                .andExpect(model().attribute("listEmployeeByName", hasItem(
		                        allOf(
		                                hasProperty("id", is(1)),
		                                hasProperty("firstName", is("Employee1")),
		                                hasProperty("lastName", is("abc")),
		                                hasProperty("salary", is(20000.0F))
//		                                hasProperty("emp.addressId", is(101)),
//		                                hasProperty("address", is("address1"))
		                        )
		                )))
		                .andExpect(model().attribute("listEmployeeByName", hasItem(
		                		allOf(
		                                hasProperty("id", is(2)),
		                                hasProperty("firstName", is("Employee1")),
		                                hasProperty("lastName", is("xyz")),
		                                hasProperty("salary", is(20000.0F))
//		                                hasProperty("addressId", is(101)),
//		                                hasProperty("address", is("address2"))
		                        )
		                )));
			}
			
			@Test  //while employee data was not found 
			public void getEmpByNameTest2() throws Exception {
				List<EmployeeEntity> empList=new ArrayList<>();
				when(employeeServiceImpl.selectEmpByName("Employee1")).thenReturn(empList);
				mockMvc.perform(get("/getEmps")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("firstName", "Employee1")
						.param("action", "edit"))
				        .andExpect(status().isOk())
				        .andExpect(model().attribute("msg",is("Error,unable to find employee details!! "+"Please provide correct employee name..")))
				        .andExpect(view().name("employeeName"));
			}
			
			@Test  //while no request parameters are passed
			public void getEmpByNameTest3() throws Exception {
				mockMvc.perform(get("/getEmps")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("action", "edit"))
						.andExpect(status().isBadRequest())
						.andExpect(view().name("error"));
			}
			
		}
	 @Nested
		class listTest{
			
			@Test
			public void listTest1() throws Exception {
				List<EmployeeEntity> empList=new ArrayList<>();
				EmployeeEntity emp=new EmployeeEntity(1,"Employee1","abc",20000.0F,new Address1(101,"address1"));
				empList.add(emp);
				empList.add(new EmployeeEntity(2,"Employee1","xyz",20000.0F,new Address1(101,"address2")));
				when(employeeServiceImpl.selectEmpRecords()).thenReturn(empList);
				mockMvc.perform(get("/list"))
				        .andExpect(status().isOk())
				        .andExpect(view().name("employee-list"))
				        .andExpect(model().attribute("listEmployee", hasSize(2)))
		                .andExpect(model().attribute("listEmployee", hasItem(
		                        allOf(
		                                hasProperty("id", is(1)),
		                                hasProperty("firstName", is("Employee1")),
		                                hasProperty("lastName", is("abc")),
		                                hasProperty("salary", is(20000.0F))
		                        )
		                )))
		                .andExpect(model().attribute("listEmployee", hasItem(
		                		allOf(
		                                hasProperty("id", is(2)),
		                                hasProperty("firstName", is("Employee1")),
		                                hasProperty("lastName", is("xyz")),
		                                hasProperty("salary", is(20000.0F))
		                        )
		                )));
			}
		}
}

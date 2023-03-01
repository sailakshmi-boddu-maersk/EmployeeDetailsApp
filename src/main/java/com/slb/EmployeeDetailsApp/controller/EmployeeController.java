package com.slb.EmployeeDetailsApp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.slb.EmployeeDetailsApp.model.Address1;
import com.slb.EmployeeDetailsApp.model.Employee;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;
import com.slb.EmployeeDetailsApp.service.EmployeeServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/")
	  public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
		  return modelAndView;
	  }

	  @GetMapping("/new")
	  public ModelAndView newForm() {
		  ModelAndView modelAndView = new ModelAndView();
		  modelAndView.setViewName("employee-form");
		  return modelAndView;
	  }
	  
	  @PostMapping("/insert")
	  public void insert(Employee employee,HttpServletResponse response) throws IOException{
		  EmployeeEntity emp=modelMapper.map(employee,EmployeeEntity.class);
		  employeeServiceImpl.createEmpRecord(emp);
		  response.sendRedirect("/list");
	  }
	  
	  @GetMapping("/list")
	  public ModelAndView postEmployeeList(ModelAndView mv) {
		  List<EmployeeEntity> empList=new ArrayList<>();
		  empList=employeeServiceImpl.selectEmpRecords();
		  mv.addObject("listEmployee",empList);
		  mv.setViewName("employee-list");
		  return mv;
	  }
	  
	  @GetMapping("/edit")
	  public ModelAndView edit(@RequestParam("id") int id) {
		  ModelAndView mv=new ModelAndView();
		  EmployeeEntity emp=employeeServiceImpl.selectEmp(id);
		  mv.addObject("emp",emp);
		  mv.setViewName("employee-form");
		  return mv;
	  }
	  
//	  @PutMapping("/update")
	  @RequestMapping(value="/update", method= {RequestMethod.PUT,RequestMethod.POST})
	  public void update(Employee employee,HttpServletResponse response) throws IOException{
		  EmployeeEntity emp=modelMapper.map(employee,EmployeeEntity.class);
		  employeeServiceImpl.updateEmp(emp);
		  response.sendRedirect("/list");
	  }
//	  @DeleteMapping("/delete")
	  @RequestMapping(value="/delete", method= {RequestMethod.DELETE,RequestMethod.GET})
	  public void delete(@RequestParam("id") int id,HttpServletRequest request,HttpServletResponse response) throws IOException {
		  employeeServiceImpl.deleteEmpRecord(id);
		  response.sendRedirect("/list");
	  }
	  
	  @GetMapping("/empRec")
	  public ModelAndView empRec(@RequestParam("action")String action) {
		  
		  ModelAndView mv=new ModelAndView();
		  mv.addObject("actionEditDelete",action);
		  mv.setViewName("employeeName");
		  return mv;  
	  }
	  
	  @GetMapping("/getEmps")
	  public ModelAndView getEmpByName(@RequestParam("firstName") String firstName,
			           @RequestParam("action")String action,ModelAndView mv) {
		  mv.addObject("actionEditDelete",action);
		  List<EmployeeEntity> empList=new ArrayList<EmployeeEntity>();
		  empList=employeeServiceImpl.selectEmpByName(firstName);
		  if(empList.isEmpty()) {
			  mv.addObject("actionEditDelete",action);
			  mv.addObject("msg","Error,unable to find employee details!! "+"Please provide correct employee name..");
			  mv.setViewName("employeeName");
			  return mv;
		  }
		  else {
			  mv.addObject("actionEditDelete",action);
			  mv.addObject("listEmployeeByName",empList);
			  mv.setViewName("employeeListByName");
			  return mv;
		  }  
	  }	 
	

//		@GetMapping("/index")
//		  public String indexPage() {
//			  
//			  return "index";
//		  }
//		  
//		  @RequestMapping("/home")
//		  public String homePage() {
//			  return "index";
//		  }
//		  
//		  @RequestMapping("/login")
//		  public String login(@RequestParam("uname") String uname,@RequestParam("password") String pass,HttpSession session,Model model) {
//			  System.out.println(uname+" "+pass);
//			  if(uname.equals("sai")&& pass.equals("1234")) {
//				  session.setAttribute("admin","activeState");
//				  return "home-page";
//			  }
//			  model.addAttribute("userMsg","Please log in with valid details to continue..");
//			  return "index";
//		  }
//		  	
	  
//	  @RequestMapping("/insert")
//	  public String insert(@RequestParam("id") int id,
//			  @RequestParam("firstName")String firstName,
//			  @RequestParam("lastName")String lastName,
//			  @RequestParam("salary")float salary,
//			  @RequestParam("addressId")int addressId,
//			  @RequestParam("address")String address,
//			  HttpServletRequest request) {
//		  Address1 add=new Address1(addressId,address);
//		  Employee1 employee = new Employee1(firstName,lastName,salary,add);
//		  
//		  employeeService.createEmpRecord(employee);
//		  return "redirect:/list";
//	  }
	  
//	  @RequestMapping("/insert")
//	  public String insert(@RequestParam("firstName")String firstName,
//			  @RequestParam("lastName")String lastName,
//			  @RequestParam("salary")float salary,
//			  @RequestParam("addressId")int addressId,
//			  @RequestParam("address")String address) {
//		  Address1 add=new Address1(addressId,address);
//		  EmployeeEntity emp=EmployeeEntity.builder().firstName(firstName).lastName(lastName).salary(salary).address(add).build();
//		  employeeServiceImpl.createEmpRecord(emp);
//		  return "redirect:/list";      //sendRedirect
//	  }
//	  @RequestMapping("/update")
//	  public String update(@RequestParam("id") int id,
//			  @RequestParam("firstName")String firstName,
//			  @RequestParam("lastName")String lastName,
//			  @RequestParam("salary")float salary,
//			  @RequestParam("addressId")int addressId,
//			  @RequestParam("address")String address) {
//		  Address1 add=new Address1(addressId,address);
//		  EmployeeEntity emp=EmployeeEntity.builder().id(id).firstName(firstName).lastName(lastName).salary(salary).address(add).build();
//		  employeeServiceImpl.updateEmp(emp);
//		  return "redirect:/list";      //sendRedirect
//	  }
//	  @RequestMapping("/logout")
//	  public String logout(HttpSession session) {
//		  session.removeAttribute("admin");
//		  session.invalidate();
//		  return "index";
//	  }
}

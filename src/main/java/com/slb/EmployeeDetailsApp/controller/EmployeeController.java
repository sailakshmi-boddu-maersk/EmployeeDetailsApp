package com.slb.EmployeeDetailsApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.slb.EmployeeDetailsApp.model.Address1;
import com.slb.EmployeeDetailsApp.model.Employee;
import com.slb.EmployeeDetailsApp.model.EmployeeEntity;
import com.slb.EmployeeDetailsApp.service.EmployeeServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/")
	  public String index() {
		  return "index";
	  }

	@GetMapping("/index")
	  public String indexPage() {
		  return "index";
	  }
	  
	  @RequestMapping("/home")
	  public String homePage() {
		  return "home-page";
	  }
	  
	  @RequestMapping("/login")
	  public String login(@RequestParam("uname") String uname,@RequestParam("password") String pass,HttpSession session,Model model) {
		  System.out.println(uname+" "+pass);
		  if(uname.equals("sai")&& pass.equals("1234")) {
			  session.setAttribute("admin","activeState");
			  return "home-page";
		  }
		  model.addAttribute("userMsg","Please log in with valid details to continue..");
		  return "index";
	  }
	  
	  @RequestMapping("/new")
	  public String newForm() {
		  return "employee-form";
	  }
	  
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
	  
	  @RequestMapping("/insert")
	  public String insert(Employee employee){
		  EmployeeEntity emp=modelMapper.map(employee,EmployeeEntity.class);
		  employeeServiceImpl.createEmpRecord(emp);
		  return "redirect:/list";
	  }
	  
	  @RequestMapping("/list")
	  public String list(Model model) {
		  List<EmployeeEntity> empList=new ArrayList<>();
		  empList=employeeServiceImpl.selectEmpRecords();
		  model.addAttribute("listEmployee",empList);
		  return "employee-list";
	  }
	  
	  @RequestMapping("/edit")
	  public ModelAndView edit(@RequestParam("id") int id) {
		  ModelAndView mv=new ModelAndView();
		  EmployeeEntity emp=employeeServiceImpl.selectEmp(id);
		  mv.addObject("emp",emp);
		  mv.setViewName("employee-form");
		  return mv;
	  }
	  
	  @RequestMapping("/update")
	  public String update(@RequestParam("id") int id,
			  @RequestParam("firstName")String firstName,
			  @RequestParam("lastName")String lastName,
			  @RequestParam("salary")float salary,
			  @RequestParam("addressId")int addressId,
			  @RequestParam("address")String address) {
		  Address1 add=new Address1(addressId,address);
		  EmployeeEntity emp=EmployeeEntity.builder().firstName(firstName).lastName(lastName).salary(salary).address(add).build();
		  employeeServiceImpl.updateEmp(emp);
		  return "redirect:/list";      //sendRedirect
	  }
	  
	  @RequestMapping("/delete")
	  public String delete(@RequestParam("id") int id,HttpServletRequest request) {
		  employeeServiceImpl.deleteEmpRecord(id);
		  return "redirect:/list";
	  }
	  
	  @RequestMapping("/empRec")
	  public ModelAndView empRec(@RequestParam("action")String action) {
		  
		  ModelAndView mv=new ModelAndView();
		  mv.addObject("actionEditDelete",action);
		  mv.setViewName("employeeName");
		  return mv;  
	  }
	  
	  @RequestMapping("/getEmps")
	  public String getEmpByName(@RequestParam("firstName") String firstName,
			           @RequestParam("action")String action,Model model) {
		  model.addAttribute("actionEditDelete",action);
		  List<EmployeeEntity> empList=new ArrayList<EmployeeEntity>();
		  empList=employeeServiceImpl.selectEmpByName(firstName);
		  if(empList.isEmpty()) {
			  model.addAttribute("actionEditDelete",action);
			  model.addAttribute("msg","Error,unable to find employee details!! "+"Please provide correct employee name..");
			  return "employeeName";
		  }
		  else {
			  model.addAttribute("actionEditDelete",action);
			  model.addAttribute("listEmployeeByName",empList);
			  return "employeeListByName";
		  }  
	  }	 
	  
	  @RequestMapping("/logout")
	  public String logout(HttpSession session) {
		  session.removeAttribute("admin");
		  session.invalidate();
		  return "index";
	  }
}

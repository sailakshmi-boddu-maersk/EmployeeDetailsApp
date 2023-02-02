package com.slb.EmployeeDetailsApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	
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
	  
//	  @RequestMapping("/list")
//	  public String list(Model model) {
//		  List<Employee> empList=new ArrayList<Employee>();
//		  empList=employeeServiceImpl.selectEmpRecords();
//		  model.addAttribute("listEmployee",empList);
//		  return "employee-list";
//	  }
	  @RequestMapping("/logout")
	  public String logout(HttpSession session) {
		  session.removeAttribute("admin");
		  session.invalidate();
		  return "index";
	  }
}

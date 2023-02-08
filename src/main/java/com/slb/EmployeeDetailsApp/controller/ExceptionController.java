package com.slb.EmployeeDetailsApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.slb.EmployeeDetailsApp.exception.EmployeesAlreadyExistsException;
import com.slb.EmployeeDetailsApp.exception.NoSuchEmployeeExistsException;

@ControllerAdvice
public class ExceptionController {
	    
	
	    @ExceptionHandler({NoHandlerFoundException.class})
	    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	    public String noHandlerFound(Model model) {
	    	model.addAttribute("msg","No Handler Found Exception");
	    	return "error";
	    }
	    
	    @ExceptionHandler(value= Exception.class)
	    @ResponseStatus(value=HttpStatus.BAD_REQUEST) 
	    public String GenericExceptionHandler(Model model,Exception e) {
	    	model.addAttribute("msg", e);
	    	return "error";
	    }
	    
	    @ExceptionHandler(value=NoSuchEmployeeExistsException.class)
	    @ResponseStatus(value=HttpStatus.NOT_FOUND)
	    public String noSuchEmployee(Model model) {
	    	model.addAttribute("msg","No such Employee Found");
	    	return "error";
	    }
	    
	    @ExceptionHandler(value=EmployeesAlreadyExistsException.class)
	    @ResponseStatus(value=HttpStatus.CONFLICT)
	    public String employeeAlreadyExists(Model model) {
	    	model.addAttribute("msg","Employee with id Already Exists");
	    	return "error";
	    }
	    
	    
//		 @ExceptionHandler({NumberFormatException.class})
//	    public String numberFormatException() {
//	    	return "error";
//	    }
}

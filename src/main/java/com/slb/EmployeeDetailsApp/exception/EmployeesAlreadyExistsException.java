package com.slb.EmployeeDetailsApp.exception;

public class EmployeesAlreadyExistsException extends RuntimeException{
	 private String message;
	 
	    public EmployeesAlreadyExistsException() {}
	 
	    public EmployeesAlreadyExistsException(String msg)
	    {
	        super(msg);
	        this.message = msg;
	    }

}

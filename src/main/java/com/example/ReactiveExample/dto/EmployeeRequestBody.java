package com.example.ReactiveExample.dto;

public class EmployeeRequestBody {

	private long salary;
	private String name;
	
	
	public EmployeeRequestBody(){};
	
	public EmployeeRequestBody(long salary, String name) {
		
		this.salary = salary;
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

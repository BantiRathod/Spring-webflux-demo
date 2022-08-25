package com.example.ReactiveExample.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReactiveExample.controller.EmployeeController;
import com.example.ReactiveExample.dto.EmployeeRequestBody;
import com.example.ReactiveExample.models.Employee;
import com.example.ReactiveExample.repositories.EmployeeRepo;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class EmployeeService {

	Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeRepo employeeRepo;

	public Flux<Employee> findAllEmployees() {

		
		return employeeRepo.findAll();
	}

	public Mono<Employee> findEmployeeById(String id) {
		
		Mono<Employee> emp =  employeeRepo.findById(id);
		//emp.subscribe(System.out::println);
		System.out.println(emp);
		log.info("Received emp form data base : {}",emp);
		return emp;
	}

	
	public Mono<Employee> createEmployeeRecord(Employee emp) {

		return employeeRepo.save(emp);
	}

	
	// FIRST OF ALL EMP WOULD BE FATCHED FROM DATABASE AND SOME FILED WOULD WE SET
	// AND FINALLY UPDATED
	// EMPLOYEE DATA WOULD BE STORED INTO DATABASE.
	public Mono<Employee> updateEmloyee(String id, EmployeeRequestBody erb) {

		return employeeRepo.findById(id).flatMap(dbemp -> {
			dbemp.setName(erb.getName());
			dbemp.setSalary(erb.getSalary());
			return employeeRepo.save(dbemp);
		});
	}

	public Mono<Employee> deleteUser(String empId) {
		return employeeRepo.findById(empId)
				.flatMap(existingUser -> employeeRepo.delete(existingUser).
				 then(Mono.just(existingUser)));
	}
	

}

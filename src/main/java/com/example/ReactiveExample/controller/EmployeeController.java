package com.example.ReactiveExample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.ReactiveExample.dto.EmployeeRequestBody;
import com.example.ReactiveExample.models.Employee;
import com.example.ReactiveExample.services.EmployeeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmployeeController {

	
	Logger log = LoggerFactory.getLogger(EmployeeController.class); 
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getallemployee")
	public Flux<Employee> getAllEmployee() {
		
		
		return employeeService.findAllEmployees();
	}

	@GetMapping(path = "/getemployee/{empid}")
	public Mono<ResponseEntity<Employee>> getEmployee(@PathVariable String empid) {

		log.info("received empid : {} ", empid);
		
		

		//log.info("received data : {} ",emp );
		// defaultIfEmpty :- this is used to provide, default value on mono empty.
	return employeeService.findEmployeeById(empid).map(e -> new ResponseEntity<>(e, HttpStatus.OK)).
				defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/updateemployee/{empId}")
	public Mono<ResponseEntity<Employee>> updateEmployee(@PathVariable String id,
			@RequestBody EmployeeRequestBody erb) {
		return employeeService.findEmployeeById(id).map(e -> ResponseEntity.ok(e))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/employee")
	public Mono<Employee> saveEmployee(@RequestBody Employee emp) {
        
		Mono<Employee> em = employeeService.createEmployeeRecord(emp);
		log.info("employee saved succesfull : {}",emp);
		return em;
	}

}

package com.example.ReactiveExample.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ReactiveExample.models.Employee;

import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepo extends ReactiveMongoRepository<Employee,String> {

}

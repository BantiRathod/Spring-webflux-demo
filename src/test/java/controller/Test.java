package controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ReactiveExample.models.Employee;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest

public class Test {

	WebClient webClient = WebClient.create("http://localhost:8080");
	
	@org.junit.Test
	public void monoTest()
	{
		Mono<Employee> mono = webClient.get().uri("/getallemployee").retrieve().bodyToMono(Employee.class);
		System.out.println(mono);
	}
}

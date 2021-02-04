package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.bean.Greeting;

@RestController
public class GreetingController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greetings/{id}")
	public Greeting singleGreetings(@PathVariable String id) {
		return new Greeting(counter.incrementAndGet(), String.format("Ciao %s!", id));
	}

	@DeleteMapping("/greetings/{id}")
	public Greeting deleteGreeting(@PathVariable String id) {
		return new Greeting(new Integer(id), String.format("Eliminato %s!", id));
	}
	
	@PutMapping("/greetings/{idResource}") 
	@ResponseBody
	public ResponseEntity<Greeting> aggiornaGreeting(@PathVariable(value="idResource") String id, @RequestBody Greeting greeting) {
		System.out.println(String.format("aggiornaGreeting(%s,%s)", id, greeting.toString()));
		return  ResponseEntity.ok(new Greeting(greeting.getId(),
				String.format("Aggiornato [%s] contenuto in -%s-!", id, greeting.getContent())));
	}

}

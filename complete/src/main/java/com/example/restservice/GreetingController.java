package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greetings")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	@GetMapping("/greetings/{id}")
	public Greeting singleGreetings(@PathVariable String id) {
		return new Greeting(counter.incrementAndGet(), String.format("Ciao %s!", id));
	}

	@DeleteMapping("/greetings/{id}")
	public Greeting deleteGreeting(@PathVariable String id) {
		return new Greeting(new Integer(id), String.format("Eliminato %s!", id));
	}

	/*
	@PutMapping("/greeting/{id}")
	public Greeting aggiornaGreeting(@RequestParam(value = "id", defaultValue = "1") String id) {
		return new Greeting(new Integer(id), String.format("Aggiornato %s!", id));
	}

	@PostMapping("/greetings")
	public Greeting newGreeting(@RequestBody String body) {
		final Long number = counter.incrementAndGet();
		return new Greeting(number.intValue(), String.format("Creato %d!", number));
	}

	@DeleteMapping("/greeting/{id}")
	public Greeting greeting(@RequestParam(value = "id", defaultValue = "1") String id) {
		return new Greeting(new Integer(id), String.format("Eliminato %s!", id));
	}
*/
}

package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.bean.Greeting;

@RestController
public class GroupGreetingController {

	
	private final AtomicLong counter = new AtomicLong();


	@GetMapping("/greetings")
	public Greeting leggiGreetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format("Ciao, %s!", name));
	}
	
	@PostMapping("/greetings")
	public Greeting creaGreetings(/*@RequestParam(value = "name", defaultValue = "Nuova risorsa") String name*/) {
		return new Greeting(counter.incrementAndGet(), "Creata nuova risorsa!");
	}

}

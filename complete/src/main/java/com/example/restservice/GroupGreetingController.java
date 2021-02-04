package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.bean.Greeting;

@RestController
public class GroupGreetingController {

	
	private final AtomicLong counter = new AtomicLong();


	@GetMapping("/greetings")
	public ResponseEntity<List<Greeting>> leggiGreetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		List<Greeting> result = new ArrayList<Greeting>();
		result.add(new Greeting(1, String.format("Ciao, primo %s!", name)));
		result.add(new Greeting(2, String.format("Ciao, secondo %s!", name)));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/greetings")
	public ResponseEntity<Greeting> creaGreetings() {
		Greeting result = new Greeting(counter.incrementAndGet(), "Creata nuova risorsa!");
		return ResponseEntity.ok(result);
	}

}

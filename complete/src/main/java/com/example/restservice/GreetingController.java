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

	@PutMapping( "/greetings_old/{id}")
	public Greeting aggiornaGreeting_2(@PathVariable String id, @RequestBody MultiValueMap<String, String> formParams) {
		return new Greeting(Integer.parseInt(formParams.get("id").get(0)),
				String.format("Aggiornato contenuto in %s", formParams.get("content")));
	}
	
	@PutMapping("/greetings/{idResource}") 
	@ResponseBody
	public ResponseEntity<Greeting> aggiornaGreeting(@PathVariable(value="idResource") String id, @RequestBody Greeting greeting) {
		//Greeting greeting = new Greeting(-1, "nadadenada");
		//String id = new Long(greeting.getId()).toString();
		System.out.println(String.format("aggiornaGreeting(%s,%s)", id, greeting.toString()));
		return  ResponseEntity.ok(new Greeting(greeting.getId(),
				String.format("Aggiornato [%s] contenuto in -%s-!", greeting.getId(), greeting.getContent())));
	}

	/*
	 * @PostMapping("/greetings") public Greeting newGreeting(@RequestBody
	 * String body) { final Long number = counter.incrementAndGet(); return new
	 * Greeting(number.intValue(), String.format("Creato %d!", number)); }
	 * 
	 * 
	 */
}

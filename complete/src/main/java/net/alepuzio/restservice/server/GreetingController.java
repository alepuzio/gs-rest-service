package net.alepuzio.restservice.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.alepuzio.restservice.bean.Greeting;

@RestController
public class GreetingController {

	@GetMapping("/greetings/{id}")
	@ResponseBody
	public ResponseEntity<Greeting> singleGreetings(@PathVariable String id) {
		return ResponseEntity.ok(new Greeting(Long.parseLong(id), String.format("Ciao %s!", id)));
	}

	@DeleteMapping("/greetings/{id}")
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable String id) {
		return ResponseEntity.ok(new Greeting(new Integer(id), String.format("Eliminato %s!", id)));
	}
	
	@PutMapping("/greetings/{idResource}") 
	@ResponseBody
	public ResponseEntity<Greeting> aggiornaGreeting(@PathVariable(value="idResource") String id, @RequestBody Greeting greeting) {
		System.out.println(String.format("aggiornaGreeting(%s,%s)", id, greeting.toString()));
		Greeting updatedGreeting = new Greeting(Long.parseLong(id), greeting.getContent()); 
		return  ResponseEntity.ok(new Greeting(updatedGreeting.getId(),
				String.format("Aggiornato [%s] contenuto in -%s-!", id, updatedGreeting.getContent())));
	}

}

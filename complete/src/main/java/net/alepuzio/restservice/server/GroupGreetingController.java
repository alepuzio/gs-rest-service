package net.alepuzio.restservice.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.alepuzio.restservice.bean.Greeting;

@RestController
public class GroupGreetingController {

	@GetMapping("/greetings")
	public ResponseEntity<List<Greeting>> leggiGreetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		List<Greeting> result = new ArrayList<Greeting>();
		result.add(new Greeting(1, String.format("Ciao, primo %s!", name)));
		result.add(new Greeting(2, String.format("Ciao, secondo %s!", name)));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/greetings")
	public ResponseEntity<Greeting> creaGreetings() {
		Greeting result = new Greeting(1, "Creata nuova risorsa!");
		return ResponseEntity.ok(result);
	}

}

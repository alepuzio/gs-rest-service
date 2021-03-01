package net.alepuzio.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.server.domain.Greeting;

public class Single {
	
	private RestTemplate restTemplate;
	private int id;
	
	Single(RestTemplate newRestTemplate, int newId) {
		this.restTemplate = newRestTemplate;
		this.id = newId;
	}

	public void putSingle() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id",""+this.id);
		Greeting greeting = new Greeting(this.id, "contenuto putSingle costruttore");
		restTemplate.put(String.format("http://localhost:8080/greetings/%s", this.id), greeting, params);
	}

	public void delete() {
		restTemplate.delete(String.format("http://localhost:8080/greetings/%s", this.id));
	}

	
	public void getSingle() {
		ResponseEntity<Greeting> result = restTemplate.getForEntity(String.format("http://localhost:8080/greetings/%s", this.id),
				Greeting.class);
		final HttpStatus status = result.getStatusCode();
		final String msg = String.format("getSingle(%s)", this.id);
		if(HttpStatus.ACCEPTED == status){
			System.out.println( msg + result.getBody());			
		}else{
			System.err.println(msg + status);
		}
	}

}

package net.alepuzio.client;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.server.domain.Greeting;

public class Group {

	private RestTemplate restTemplate;
	
	Group(RestTemplate newRestTemplate){
		this.restTemplate= newRestTemplate;
	}
	
	public List<Greeting> getList() {
		ResponseEntity<List> result = this.restTemplate.getForEntity("http://localhost:8080/greetings", List.class);
		List<Greeting> getGreeting = null;
		if(HttpStatus.ACCEPTED == result.getStatusCode()){
			getGreeting = result.getBody();
			System.out.println("getList():"+getGreeting);		
		}else{
			System.err.println("getList():"+result.getStatusCode());
		}
		return getGreeting;
	}
	
	public Greeting post() {
		Greeting greeting = new Greeting(-2, "creato da client REST");
		ResponseEntity<Greeting> result = restTemplate.postForEntity("http://localhost:8080/greetings/", greeting, Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if(HttpStatus.ACCEPTED == status){
			System.out.println("post():" + result.getBody());			
		}else{
			System.err.println("post():" + status);
		}
		return result.getBody();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}


}

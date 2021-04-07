package net.alepuzio.restservice.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;

public class Operation {
	
	private RestTemplate restTemplate;
	private Logger logger = Logger.getLogger(this.getClass());
	
	Operation(RestTemplate newRestTemplate) {
		this.restTemplate = newRestTemplate;
	}


	public void getList() {
		ResponseEntity<List> result = this.restTemplate.getForEntity("http://localhost:8080/greetings", List.class);
		if(HttpStatus.ACCEPTED == result.getStatusCode()){
			List<Greeting> getGreeting = result.getBody();
			logger.info("getList():"+getGreeting);
			
		}
	}



	public void post() {
		Greeting greeting = new Greeting(-2, "creato da client REST");
		ResponseEntity<Greeting> result = restTemplate.postForEntity("http://localhost:8080/greetings/", greeting, Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if(HttpStatus.ACCEPTED == status){
			logger.info("post():" + result.getBody());			
		}else{
			System.err.println("post():" + status);
		}
	}

	public void putSingle(int id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id",""+id);
		Greeting greeting = new Greeting(id, "contenuto putSingle costruttore");
		restTemplate.put(String.format("http://localhost:8080/greetings/%s", id), greeting, params);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}

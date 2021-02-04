package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.restservice.bean.Greeting;

@Component
public class ClientApplication {

	private RestTemplate restTemplate;

	ClientApplication(RestTemplate newRestTemplate) {
		this.restTemplate = newRestTemplate;
	}

	public static void main(String[] a) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			ClientApplication application = new ClientApplication(restTemplate);
			application.delete(12);
			application.getSingle(34);
			application.getList();
			application.post();
			application.putSingle(45);
			
			System.out.println("Application terminata");
		} catch (HttpClientErrorException e) {
			System.err.println("error:  " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getList() {
		ResponseEntity<List> result = this.restTemplate.getForEntity("http://localhost:8080/greetings", List.class);
		if(HttpStatus.ACCEPTED == result.getStatusCode()){
			List<Greeting> getGreeting = result.getBody();
			System.out.println("getList():"+getGreeting);
			
		}
	}

	public void getSingle(long id) {
		ResponseEntity<Greeting> result = restTemplate.getForEntity(String.format("http://localhost:8080/greetings/%s", id),
				Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if(HttpStatus.ACCEPTED == status){
			System.out.println("getSingle("+id+"):" + result.getBody());			
		}else{
			System.err.println("getSingle("+id+"):" + status);
		}
	}

	public void delete(long id) {
		restTemplate.delete(String.format("http://localhost:8080/greetings/%s", id));
	}

	public void post() {
		Greeting greeting = new Greeting(-2, "creato da client REST");
		ResponseEntity<Greeting> result = restTemplate.postForEntity("http://localhost:8080/greetings/", greeting, Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if(HttpStatus.ACCEPTED == status){
			System.out.println("post():" + result.getBody());			
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
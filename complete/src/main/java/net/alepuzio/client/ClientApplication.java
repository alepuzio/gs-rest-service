package net.alepuzio.client;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.server.domain.Greeting;

@Component
public class ClientApplication {



	public static void main(String[] a) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			
			Single singleGet = new Single(restTemplate, 34);
			singleGet.getSingle();
			Single singleDelete = new Single(restTemplate, 12);
			singleDelete.delete();
			Single singleUpdate = new Single(restTemplate, 45);
			singleUpdate.putSingle();
			Group application = new Group(restTemplate);
			application.getList();
			application.post();
			
			System.out.println("Application terminata");
		} catch (HttpClientErrorException e) {
			System.err.println("error:  " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}






}
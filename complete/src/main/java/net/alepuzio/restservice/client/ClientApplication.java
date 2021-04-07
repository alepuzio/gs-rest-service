package net.alepuzio.restservice.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.operation.Get;

@Component
public class ClientApplication {


	private Logger logger = Logger.getLogger(this.getClass());
	

	public static void main(String[] a) {
		try {
			ClientApplication clientApplication = new ClientApplication();
			Operation application = new Operation(clientApplication.restTemplate());
			application.delete(12);
			
			HTTPVerb httpVerb = new Get(new Op(34));
			httpVerb.execute();
			
			HTTPVerb delete = new Delete(new Op(12));
			delete.execute();
			
			application.getList();
			application.post();
			application.putSingle(45);
			clientApplication.close();			
		} catch (HttpClientErrorException e) {
			System.err.println("error:  " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}
	
	private void close(){
		logger.info("Stop application client");
	}

}
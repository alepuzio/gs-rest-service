package net.alepuzio.restservice.client.operation;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.HTTPVerb;

public class Authenticate implements HTTPVerb {

	private  HTTPVerb origin;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Authenticate(HTTPVerb newOrigin){
		this.origin = newOrigin;
	}

	@Override
	public long id() {
		return this.origin.id();
	}

	@Override
	public void execute() {
		Greeting greeting = new Greeting(-2, "creato da client REST");
		ResponseEntity<Greeting> result = this.origin.restTemplate().postForEntity("http://localhost:8080/authenticate/", greeting, Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if(HttpStatus.ACCEPTED == status){
			logger.info(String.format("post():%s",result.getBody()));			
		}else{
			logger.error(String.format("post():%s", status));
		}

	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();	
	}
}

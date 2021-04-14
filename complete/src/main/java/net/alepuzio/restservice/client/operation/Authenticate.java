package net.alepuzio.restservice.client.operation;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
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
		Greeting greeting = new Greeting( id(), "Authenticate by the REST client");
		String resource = String.format("%s/authenticate/", this.url());
		ResponseEntity<Greeting> result = this.origin.restTemplate().postForEntity(resource, greeting, Greeting.class);
		final HttpStatus status = result.getStatusCode();
		final int statusCode = result.getStatusCodeValue();
		if(HttpStatus.ACCEPTED == status){
			logger.info(String.format("post():%s[%d]", result.getBody(), statusCode));			
		}else{
			logger.error(String.format("post():%s[%d]->%s", status, statusCode, result.getHeaders()));
		}

	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();	
	}
	
	@Override
	public String url() {
		return this.origin.url();
	}
}

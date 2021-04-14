package net.alepuzio.restservice.client.operation;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.AuthGreeting;
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
		AuthGreeting greeting = new AuthGreeting( id(),"REST client","username","password");
		String resource = String.format("%s", this.origin.url());
		ResponseEntity<AuthGreeting> result = this.origin.restTemplate().postForEntity(resource, greeting, AuthGreeting.class);
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

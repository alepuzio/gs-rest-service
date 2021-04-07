package net.alepuzio.restservice.client.operation;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.HTTPVerb;

public class GetSingle implements HTTPVerb {
	
	private final HTTPVerb origin;
	
	public GetSingle(HTTPVerb newOrigin){
		this.origin = newOrigin;
	}
	
	
	private Logger logger = Logger.getLogger(this.getClass());

	public void execute() {
		ResponseEntity<Greeting> result = this.restTemplate().getForEntity(
				String.format("http://localhost:8080/greetings/%s", id()),
				Greeting.class);
		final HttpStatus status = result.getStatusCode();
		if ( HttpStatus.ACCEPTED == status ) {
			logger.info(String.format("getSingle(%s):%s", this.id(), result.getBody()));			
		} else {
			System.err.println(String.format("getSingle(%s): %s", this.id(), status));
		}
	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();
	}

	@Override
	public long id() {
		return this.origin.id();
	}

}

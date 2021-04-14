package net.alepuzio.restservice.client.operation;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.HTTPVerb;

public class Post implements HTTPVerb {

	private HTTPVerb origin;
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	public Post(HTTPVerb newHttpVerb) {
		super();
		this.origin = newHttpVerb;
	}

	@Override
	public long id() {
		return this.origin.id();
	}

	@Override
	public void execute() {
		logger.info(String.format(">post(%s)", id() ));	
		Greeting greeting = new Greeting(-2, "creato da client REST");
		ResponseEntity<Greeting> result = this.origin.restTemplate().postForEntity(
				String.format("%s/", this.url()), 
				greeting,
				Greeting.class);
		final HttpStatus status = result.getStatusCode();
		final int statusCode = result.getStatusCodeValue();
		if (HttpStatus.ACCEPTED == status) {
			logger.info(String.format("post():[%d]\n->%s", statusCode, result.getBody() ));			
		} else {
			logger.error(String.format("post():%s[%d]\n>%s\n", status, statusCode, result.getHeaders()));
		}
		logger.info(String.format("<post(%s)\n**************************" , this.id()));
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

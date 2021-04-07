package net.alepuzio.restservice.client.operation;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.HTTPVerb;

public class GetMore implements HTTPVerb {
	private HTTPVerb origin;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public GetMore ( HTTPVerb newHttpVerb ) {
		super();
		this.origin = newHttpVerb;
	}

	@Override
	public long id() {
		return this.origin.id();
	}

	@Override
	public void execute() {
			ResponseEntity<List> result = this.restTemplate().getForEntity("http://localhost:8080/greetings", List.class);
			if(HttpStatus.ACCEPTED == result.getStatusCode()){
				List<Greeting> getGreeting = result.getBody();
				logger.info("getList():"+getGreeting);
			}
	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();
	}

}

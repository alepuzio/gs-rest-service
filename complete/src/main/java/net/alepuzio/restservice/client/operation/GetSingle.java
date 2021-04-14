package net.alepuzio.restservice.client.operation;

import java.util.List;

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
		logger.info(String.format(">GetSingle(%s)" , this.id()));
		ResponseEntity<Greeting> result = this.restTemplate().getForEntity(
						String.format("%s/%s", this.url(), this.id()), 
						Greeting.class);
		final HttpStatus status = result.getStatusCode();
		final int statusCode = result.getStatusCodeValue();
		if (HttpStatus.ACCEPTED == status) {
			logger.info(String.format("getSingle():[%d]\n->%s", statusCode, result.getBody() ));			
		} else {
			logger.error(String.format("getSingle():%s[%d]\n->%s", status, statusCode, result.getHeaders()));
		}
		logger.info(String.format("<GetSingle(%s)\n**************************" , this.id()));
	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();
	}

	@Override
	public long id() {
		return this.origin.id();
	}
	
	@Override
	public String url() {
		return this.origin.url();
	}

}

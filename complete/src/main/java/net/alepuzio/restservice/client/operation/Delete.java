package net.alepuzio.restservice.client.operation;

import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.HTTPVerb;

public class Delete implements HTTPVerb {

	private HTTPVerb origin;
	
	public Delete(HTTPVerb newHttpVerb) {
		super();
		this.origin = newHttpVerb;
	}

	@Override
	public long id() {
		return this.origin.id();
	}

	@Override
	public void execute() {
		 this.origin.restTemplate().delete(String.format("http://localhost:8080/greetings/%s", id()));
	}

	@Override
	public RestTemplate restTemplate() {
		return this.origin.restTemplate();
	}

}

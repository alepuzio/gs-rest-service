package net.alepuzio.restservice.client;

import org.springframework.web.client.RestTemplate;

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

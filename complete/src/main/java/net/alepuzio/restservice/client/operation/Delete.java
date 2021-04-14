package net.alepuzio.restservice.client.operation;

import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
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
		 this.origin.restTemplate().delete(String.format("%s/%s", this.url(),  id()), Greeting.class);
		 
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

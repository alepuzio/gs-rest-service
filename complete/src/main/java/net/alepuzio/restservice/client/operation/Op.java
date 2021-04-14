package net.alepuzio.restservice.client.operation;

import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.HTTPVerb;

public class Op implements HTTPVerb {

	private final RestTemplate restTemplate; 
	private  Resource resource;
	
	public Op(Resource resource){
		this.restTemplate = resource.restTemplate();
		this.resource = resource;
	}

	@Override
	public long id() {
		return this.resource.id();
	}

	@Override
	public void execute() {
		throw new UnsupportedOperationException("Unexpected Operation");
	}

	@Override
	public RestTemplate restTemplate() {
		return this.restTemplate;
	}

	@Override
	public String url() {
		return resource.path();
	}

	
}

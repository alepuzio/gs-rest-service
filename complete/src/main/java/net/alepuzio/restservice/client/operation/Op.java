package net.alepuzio.restservice.client.operation;

import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.HTTPVerb;

public class Op implements HTTPVerb {

	private final long id ;
	private final RestTemplate restTemplate; 
	public Op(long newId, RestTemplate newRestTemplate){
		this.id = newId;
		this.restTemplate = newRestTemplate;
	}

	@Override
	public long id() {
		return this.id;
	}

	@Override
	public void execute() {
		throw new UnsupportedOperationException("Unexpected Operation");
	}

	@Override
	public RestTemplate restTemplate() {
		return this.restTemplate;
	}

	
}

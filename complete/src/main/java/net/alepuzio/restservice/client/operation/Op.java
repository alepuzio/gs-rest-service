package net.alepuzio.restservice.client.operation;

import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.HTTPVerb;

public class Op implements HTTPVerb {

	public final long id ;
	
	public Op(long newId){
		this.id = newId;
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
		throw new UnsupportedOperationException("Unplanned RestTemplate");
	}

	
}

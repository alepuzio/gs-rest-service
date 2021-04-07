package net.alepuzio.restservice.client;

import org.springframework.web.client.RestTemplate;

public class Op implements HTTPVerb {

	public final long id ;
	
	Op(long newId){
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

package net.alepuzio.restservice.client.operation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.bean.Greeting;
import net.alepuzio.restservice.client.HTTPVerb;

public class Put implements HTTPVerb {


	private HTTPVerb origin;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Put(HTTPVerb newHttpVerb) {
		super();
		this.origin = newHttpVerb;
	}

	@Override
	public long id() {
		return this.origin.id();
	}

	@Override
	public void execute() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id",""+this.id());
		Greeting greeting = new Greeting(this.id(), "contenuto putSingle costruttore");
		this.restTemplate().put(
				String.format("%s/%s", this.url(), this.id()), 
				greeting,
				params
		);
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

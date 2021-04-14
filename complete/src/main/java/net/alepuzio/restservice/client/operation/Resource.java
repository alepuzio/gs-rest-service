package net.alepuzio.restservice.client.operation;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Resource {

	private String path;
	private long id;
	private RestTemplate restTemplate = new RestTemplate();
	
	public Resource(String newPath, long newId) {
		super();
		this.path = newPath;
		this.id = newId;
		this. restTemplate = new RestTemplate();
	}
	
	public long id() {
		return id;
	}
	
	public String path(){
		return this.path;
	}
	
	public RestTemplate restTemplate(){
		if(null == this.restTemplate.getMessageConverters() || this.restTemplate.getMessageConverters().isEmpty()){
			this.restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			this.restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		}
		return this.restTemplate;
	}

}

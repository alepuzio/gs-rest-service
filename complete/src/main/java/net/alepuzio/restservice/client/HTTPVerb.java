package net.alepuzio.restservice.client;

import org.springframework.web.client.RestTemplate;
/**
 * @overview: every operation of HTTP verb impelemtns this contract
 * */
public interface HTTPVerb {
	public void execute();
	public RestTemplate restTemplate();
	public String url();
	public long id();
}

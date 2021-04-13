package net.alepuzio.restservice.client;

import org.apache.log4j.Logger;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.operation.Delete;
import net.alepuzio.restservice.client.operation.GetMore;
import net.alepuzio.restservice.client.operation.GetSingle;
import net.alepuzio.restservice.client.operation.Op;
import net.alepuzio.restservice.client.operation.Post;

@Component
public class ClientApplication {


	private Logger logger = Logger.getLogger(this.getClass());
	
	public static void main(String[] a) {
		try {
			ClientApplication clientApplication = new ClientApplication();
			clientApplication.start();
			HTTPVerb httpVerb = new GetSingle(new Op(34, clientApplication.restTemplate()));
			httpVerb.execute();
			HTTPVerb delete = new Delete(new Op(12, clientApplication.restTemplate()));
			delete.execute();
			HTTPVerb post = new Post(new Op(12, clientApplication.restTemplate()));
			post.execute();
			HTTPVerb getMore = new GetMore(new Op(12, clientApplication.restTemplate()));
			getMore.execute();
			HTTPVerb authenticate = new Post(new Authenticate(new Op(12, clientApplication.restTemplate())));
			getMore.execute();
			clientApplication.stop();			
		} catch (HttpClientErrorException e) {
			System.err.println("error:  " + e.getResponseBodyAsString());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		return restTemplate;
	}

	private void start(){
		logger.info("Start application client");
	}

	private void stop(){
		logger.info("Stop application client");
	}

}
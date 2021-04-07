package net.alepuzio.restservice.client;

import org.apache.log4j.Logger;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.operation.Delete;
import net.alepuzio.restservice.client.operation.Get;
import net.alepuzio.restservice.client.operation.Op;
import net.alepuzio.restservice.client.operation.Post;

@Component
public class ClientApplication {


	private Logger logger = Logger.getLogger(this.getClass());
	
	public static void main(String[] a) {
		try {
			ClientApplication clientApplication = new ClientApplication();
			Operation application = new Operation(clientApplication.restTemplate());
			
			HTTPVerb httpVerb = new Get(new Op(34));
			httpVerb.execute();
			HTTPVerb delete = new Delete(new Op(12));
			delete.execute();
			
			HTTPVerb post = new Post(new Op(12));
			post.execute();
	
			application.getList();
			
			application.putSingle(45);
			
			clientApplication.close();			
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
	
	private void close(){
		logger.info("Stop application client");
	}

}
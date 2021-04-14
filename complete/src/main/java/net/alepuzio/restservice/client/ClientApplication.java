package net.alepuzio.restservice.client;

import org.apache.log4j.Logger;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import net.alepuzio.restservice.client.operation.Authenticate;
import net.alepuzio.restservice.client.operation.Delete;
import net.alepuzio.restservice.client.operation.GetMore;
import net.alepuzio.restservice.client.operation.GetSingle;
import net.alepuzio.restservice.client.operation.Op;
import net.alepuzio.restservice.client.operation.Post;
import net.alepuzio.restservice.client.operation.Resource;

@Component
public class ClientApplication {


	protected final Logger logger = Logger.getLogger(this.getClass());
	
	public static void main(String[] a) {
		Resource singleGreeting = new Resource("http://localhost:8080/greetings/", 34);
		Resource moreGreeting = new Resource("http://localhost:8080/greetings/", -1);
		ClientApplication clientApplication = new ClientApplication();
		Resource singleAuthGreeting = new Resource("http://localhost:8080/auth/greetings/", 34);
		try {
			clientApplication.start();
			HTTPVerb httpVerb = new GetSingle(new Op( singleGreeting));
			httpVerb.execute();
			HTTPVerb delete = new Delete(new Op( singleGreeting));
			delete.execute();
			HTTPVerb post = new Post(new Op(singleGreeting));
			post.execute();
			HTTPVerb getMore = new GetMore(new Op( moreGreeting));
			getMore.execute();
			HTTPVerb authenticate = new Authenticate(new Op(singleAuthGreeting));
			authenticate.execute();
			clientApplication.stop();			
		} catch (HttpClientErrorException e) {
			clientApplication.logger.fatal("error:  " + e.getResponseBodyAsString());
			clientApplication.logger.fatal(e);
		} catch (Exception e) {
			clientApplication.logger.fatal(e);
		}
	}

	private void start(){
		logger.info("Start application client");
	}

	private void stop(){
		logger.info("Stop application client");
	}

}
package net.alepuzio.restservice.server.jwt;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
/**
 * from https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 * */
@Component
public class JwtUserDetailsService implements UserDetailsService {
	
	public UserDetails loadUserByUsername(String name){
		return new User( name);
	}

}

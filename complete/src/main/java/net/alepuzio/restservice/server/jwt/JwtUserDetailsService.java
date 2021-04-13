package net.alepuzio.restservice.server.jwt;

import org.springframework.security.core.userdetails.UserDetails;
/**
 * from https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 * */
public class JwtUserDetailsService {
	
	public UserDetails loadUserByUsername(String name){
		return new User( name);
	}

}

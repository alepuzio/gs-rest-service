package net.alepuzio.restservice.server.jwt;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * from https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 * */
@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public boolean canTokenBeRefreshed(String value){
		return false;
	}
	
	public String refreshToken(String token){
		return String.format("%s-%s", token, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
	}
	/**
	 * retrieve username from jwt token
	 * */
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public UserDetails getUserDetails(String token){
		return new User(getUsernameFromToken(token), "", null, true,true);
	}

	/**
	 * retrieve expiration date from jwt token
	 * */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(getAllClaimsFromToken(token));
	}

	/**
	 * for retrieveing any information from token we will need the secret key
	 * */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/***
	 * check if the token has expired
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(String token) {
		return getExpirationDateFromToken(token).before(new Date());
	}

	/***
	 * generate token for user
	 * @param userDetails
	 * @return
	 */
	public String generateToken(UserDetails userDetails) {
		return doGenerateToken(new HashMap<>(), userDetails.getUsername());
	}

	/*
	 * while creating the token -
	 
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the
	// ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	*/
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))//TOOD using string.format
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/***
	 * validate token
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		return (getUsernameFromToken(token).equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
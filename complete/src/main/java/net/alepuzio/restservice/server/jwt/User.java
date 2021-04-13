package net.alepuzio.restservice.server.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * from https://dzone.com/articles/spring-boot-security-json-web-tokenjwt-hello-world
 * */
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5758892592780127158L;
	private String username;
	private String password;
	private Date expirationDate;
	private Boolean locked;
	private Boolean enabled;

	public User(String username){
		this.username = username;
		this.password = "EMPTY";
		this.expirationDate = new Date();
		this.locked = false;
		this.enabled = false;
	}
	public User(String username, String password, Date expirationDate, Boolean locked, Boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.expirationDate = expirationDate;
		this.locked = locked;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return expirationDate.after(new Date());
	}

	@Override
	public boolean isAccountNonLocked() {
		return !(this.locked);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return expirationDate.after(new Date());
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
}

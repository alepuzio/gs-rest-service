package net.alepuzio.restservice.server.jwt_old;

import java.util.List;

public class User_old {
    private String username;

    private String password;

    private Boolean enabled;

    private List<Authority_old> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Authority_old> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority_old> authorities) {
		this.authorities = authorities;
	}
    
}
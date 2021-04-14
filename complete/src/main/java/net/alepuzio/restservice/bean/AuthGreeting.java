package net.alepuzio.restservice.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthGreeting {
	@JsonProperty("Id")
	private  long id;
	@JsonProperty("Content")	
	private  String content;
	@JsonProperty("username")	
	private  String username;
	@JsonProperty("password")	
	private  String password;
	
	public AuthGreeting(long id, String content, String username,
			String password) {
		this.id = id;
		this.content = content;
		this.username = username;
		this.password = password;
	}
	/*with no empty constructor I receive
	 * Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
	 * Cannot construct instance of `com.example.restservice.bean.Greeting` (no Creators, like default constructor, exist): 
	 * cannot deserialize from Object value (no delegate- or property-based Creator)
	 * when the client calls POST and GET */
	public AuthGreeting() {
		super();
	}
	public long getId() {
		return id;
	}
	
	public String getContent() {
		return String.format("Authenticated %s",content);
	}

	@Override
	public String toString() {
		return String.format("AuthGreeting[%s,%s,%s,%s]", id, content,username,password);
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}

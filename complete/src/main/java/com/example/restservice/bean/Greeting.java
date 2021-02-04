package com.example.restservice.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
	@JsonProperty("Id")
	private  long id;
	@JsonProperty("Content")	
	private  String content;

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	/*senza costruttore vuoto ho un
	 * Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
	 * Cannot construct instance of `com.example.restservice.bean.Greeting` (no Creators, like default constructor, exist): 
	 * cannot deserialize from Object value (no delegate- or property-based Creator)
	 * quando chiamo la POST e le GET da client */
	public Greeting() {
		super();
	}
	public long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return String.format("Greeting[%s,%s]", id, content);
	}
}

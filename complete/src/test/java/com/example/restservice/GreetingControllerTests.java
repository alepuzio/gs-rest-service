/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {
		this.mockMvc.perform(get("/greetings/12"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Ciao 12!"));
	}
	@Test
	public void deleteGreeting() throws Exception {
		this.mockMvc.perform( delete("/greetings/12") )
        		.andExpect( status().isOk() );
	}

	@Test
	public void putGreeting() throws Exception {
		this.mockMvc.perform( put("/greetings/13")
		          .contentType(MediaType.APPLICATION_JSON_VALUE)
		          .content("{\"id\":\"13\",\"content\":\"altro\"}") 
					.accept(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").value("Aggiornato [13] contenuto in -altro-!"));
	}


	
/***********
 * Test commentati lasciati per documentazione
 * 
 * **/
/*
 * 	@Test error: {id} is not parsed because param() does'nt affect the path parameter, only the request parameter
	public void failParamGreetingShouldReturnTailoredMessage() throws Exception {
		this.mockMvc.perform(get("/greetings/{id}").param("id", "13"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Ciao 13!"));
	}
*/
	

	
}

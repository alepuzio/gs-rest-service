package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupGreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getListGreetings() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/greetings")).andDo(print())
		.andExpect(status().isOk()).andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals("[{\"Id\":1,\"Content\":\"Ciao, primo World!\"},{\"Id\":2,\"Content\":\"Ciao, secondo World!\"}]", result);
		;
	}

	@Test
	public void postGreeting() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(post("/greetings"))
				.andDo(print()).andExpect(status().isOk())
					.andReturn();	
		String result = mvcResult.getResponse().getContentAsString();
		Assertions.assertEquals("{\"Id\":1,\"Content\":\"Creata nuova risorsa!\"}", result);
	
	}


}

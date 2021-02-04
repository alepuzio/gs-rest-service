package com.example.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GroupGreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getListGreetings() throws Exception {
		this.mockMvc.perform(get("/greetings")).andDo(print())
		.andExpect(status().isOk())
				//.andExpect(jsonPath("$.content").value("Ciao, World!"))
		;
	}

	//@Test
	public void postGreeting() throws Exception {
		this.mockMvc.perform( post("/greetings") )
		.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.content").value("Creata nuova risorsa!"));
	}


}

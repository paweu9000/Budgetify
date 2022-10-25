package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class BudgetifyApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void unauthorizedUserGetEmailRequest() throws Exception {
		mvc.perform(get("/api/user/email"))
				.andExpect(status().isUnauthorized());
	}

}

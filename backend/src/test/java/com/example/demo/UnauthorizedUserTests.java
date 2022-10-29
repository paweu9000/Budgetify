package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UnauthorizedUserTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void unauthorizedUserGetEmailRequest() throws Exception {
		mvc.perform(get("/api/user/email"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetUsernameRequest() throws Exception {
		mvc.perform(get("/api/user/name"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetBalanceRequest() throws Exception {
		mvc.perform(get("/api/user/balance"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetSavingsRequest() throws Exception {
		mvc.perform(get("/api/user/savings"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetTransactionRequest() throws Exception {
		mvc.perform(get("/api/transaction/1"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllTransactionsRequest() throws Exception {
		mvc.perform(get("/api/transaction/all"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserPostTransactionRequest() throws Exception {
		mvc.perform(post("/api/transaction"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllTransactionsByDayRequest() throws Exception {
		mvc.perform(get("/api/transaction/all/7"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllLoanTransactionsRequest() throws Exception {
		mvc.perform(get("/api/loan/all"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllSpendingsTransactionsRequest() throws Exception {
		mvc.perform(get("/api/spendings/all"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllSavingsTransactionsRequest() throws Exception {
		mvc.perform(get("/api/savings/all"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void unauthorizedUserGetAllIncomeTransactionsRequest() throws Exception {
		mvc.perform(get("/api/income/all"))
				.andExpect(status().isUnauthorized());
	}

}

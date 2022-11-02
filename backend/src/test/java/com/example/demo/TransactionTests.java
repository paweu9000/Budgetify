package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionTests {

    @Autowired
    MockMvc mvc;

    @Test
    @WithUserDetails("test@email.com")
    public void validTransactionPostTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"test_description\", \"budgetType\": \"SAVINGS\"," +
                        "\"amount\": \"200.0\"}");
        mvc.perform(mockRequest).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void invalidTransactionPostTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"description\": \" \", \"budgetType\": \"\"," +
                        "\"amount\": \"\"}");
        mvc.perform(mockRequest).andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getCurrentUserTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/25")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getOtherUserTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getTransactionNullTest() throws Exception {
        mvc.perform(get("/api/transaction/9999")).andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllTransactionsTest() throws Exception {
        mvc.perform(get("/api/transaction/all")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllTransactionByDaysTest() throws Exception {
        mvc.perform(get("/api/transaction/all/7")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllSavingsTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/savings/all")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllLoanTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/loan/all")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllIncomeTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/income/all")).andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("test@email.com")
    public void getAllSpendingsTransactionTest() throws Exception {
        mvc.perform(get("/api/transaction/spendings/all")).andExpect(status().isOk());
    }
}

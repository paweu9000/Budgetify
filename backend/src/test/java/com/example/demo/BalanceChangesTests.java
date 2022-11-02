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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BalanceChangesTests {

    @Autowired
    MockMvc mvc;

    @Test
    @WithUserDetails("test@email.com")
    public void BalanceChangeAfterTransactionTest() throws Exception {
        MvcResult resultBefore = mvc.perform(get("/api/user/balance")).andReturn();
        Double balanceBeforeTransaction = Double.parseDouble(resultBefore.getResponse().getContentAsString());
        MockHttpServletRequestBuilder mockRequest = post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"test_description\", \"budgetType\": \"INCOME\"," +
                        "\"amount\": \"200.0\"}");
        mvc.perform(mockRequest).andExpect(status().isOk());
        MvcResult resultAfter = mvc.perform(get("/api/user/balance")).andReturn();
        Double balanceAfterTransaction = Double.parseDouble(resultAfter.getResponse().getContentAsString());
        assertEquals(balanceBeforeTransaction, (balanceAfterTransaction - 200.0));
    }

    @Test
    @WithUserDetails("test@email.com")
    public void BalanceChangeAfterDeletingTransactionTest() throws Exception {
        MvcResult resultBefore = mvc.perform(get("/api/user/balance")).andReturn();
        Double balanceBeforeTransaction = Double.parseDouble(resultBefore.getResponse().getContentAsString());
        MockHttpServletRequestBuilder mockRequest = post("/api/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"test_description\", \"budgetType\": \"INCOME\"," +
                        "\"amount\": \"200.0\"}");
        MvcResult response = mvc.perform(mockRequest).andExpect(status().isOk()).andReturn();
        String json = response.getResponse().getContentAsString();
        int indexOfId = json.indexOf("=");
        int endIndexOfId = json.indexOf(",");
        String idOfTransaction = json.substring(indexOfId+1, endIndexOfId);
        System.out.println(idOfTransaction);
        mvc.perform(delete("/api/transaction/" + idOfTransaction));
        MvcResult resultAfter = mvc.perform(get("/api/user/balance")).andReturn();
        Double balanceAfterTransaction = Double.parseDouble(resultAfter.getResponse().getContentAsString());
        assertEquals(balanceBeforeTransaction, balanceAfterTransaction);
    }
}

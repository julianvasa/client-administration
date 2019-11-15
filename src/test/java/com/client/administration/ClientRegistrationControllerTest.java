package com.client.administration;

import com.client.administration.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private final String newClient = "{\"firstName\":\"First\",\"lastName\":\"Last\",\"email\":\"test@test.com\",\"address\":{\"street\":\"My address\",\"city\":\"Tirane\",\"country\":\"Albania\"},\"phone\":\"1213123213\"}";
    private final String incorrectData = "{\"lastName\":\"Last\",\"email\":\"test@test.com\",\"address\":{\"street\":\"My address\",\"city\":\"Tirane\",\"country\":\"Albania\"},\"phone\":\"1213123213\"}";


    @Test
    public void testRegistrationApi() throws Exception {
        MvcResult response = mockMvc.perform(post("/api/clients")
            .content(newClient)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();
        Client clientResponse = mapper.readValue(response.getResponse().getContentAsString(), Client.class);
        assertNotNull(clientResponse);
    }

    @Test
    public void testRegistrationApiWithIncorrectInputData() throws Exception {
        mockMvc.perform(post("/api/clients")
            .content(incorrectData)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
    }
}

package com.client.administration;

import com.client.administration.model.Client;
import com.client.administration.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper mapper;

    private final String newClient = "{\"firstName\":\"First\",\"lastName\":\"Last\",\"email\":\"test@test.com\",\"address\":{\"street\":\"My address\",\"city\":\"Tirane\",\"country\":\"Albania\"},\"phone\":\"1213123213\"}";
    private final String incorrectData = "{\"lastName\":\"Last\",\"email\":\"test@test.com\",\"address\":{\"street\":\"My address\",\"city\":\"Tirane\",\"country\":\"Albania\"},\"phone\":\"1213123213\"}";
    private final String testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpdXMiLCJleHAiOjE1NzQ5NzQzMjd9.map7uMUItdOXrQvR8rxrOxBi6xU2Guc3Ns5WfgTFPTS4HWLBe_tfqqOJc5Bc_CunF6MzE6IcE6ILIX-eHaCknA";
    private final String incorrectToken = "XXXXXeyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWxpdXMiLCJleHAiOjE1NzQ5NzQzMjd9.map7uMUItdOXrQvR8rxrOxBi6xU2Guc3Ns5WfgTFPTS4HWLBe_tfqqOJc5Bc_CunF6MzE6IcE6ILIX-eHaCknA";
    private final String deactivate = "{\"deactivated\": true}";

    @Before
    public void insertSampleClient(){
        Client client = Client.builder()
            .firstName("testFirst")
            .lastName("testLast")
            .email("test@test.com")
            .build();
        clientRepository.save(client);
    }

    @Test
    public void whenCreatingNewClientWithCorrectData_thenClientIsCreated() throws Exception {
        MvcResult response = mockMvc.perform(post("/api/clients")
            .header("Authorization", "Bearer " + testToken)
            .content(newClient)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();
        Client clientResponse = mapper.readValue(response.getResponse().getContentAsString(), Client.class);
        assertNotNull(clientResponse);
    }

    @Test
    public void whenCreatingNewClientWithIncorrectData_thenClientIsNotCreatedAndResponseCodeIsBadRequest() throws Exception {
        mockMvc.perform(post("/api/clients")
            .header("Authorization", "Bearer " + testToken)
            .content(incorrectData)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();
    }

    @Test
    public void whenCreatingNewClientWithoutAuthenticationToken_thenReturnForbidden() throws Exception {
        mockMvc.perform(post("/api/clients")
            .content(newClient)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden())
            .andReturn();
    }

    @Test
    public void whenCreatingNewClientWithIncorrectAuthenticationToken_thenReturnForbidden() throws Exception {
        mockMvc.perform(post("/api/clients")
            .header("Authorization", "Bearer " + incorrectToken)
            .content(newClient)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden())
            .andReturn();
    }

    @Test
    public void whenRetrievingListOfAllClientsWithAuthenticationToken_thenReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/clients")
            .header("Authorization", "Bearer " + testToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void whenSearchingClientsWithAuthenticationToken_thenReturnSuccess() throws Exception {
        mockMvc.perform(get("/api/clients/search")
            .header("Authorization", "Bearer " + testToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    public void whenDeactivatingClientWithAuthenticationToken_thenReturnSuccess() throws Exception {
        MvcResult response = mockMvc.perform(put("/api/clients/1")
            .header("Authorization", "Bearer " + testToken)
            .content(deactivate)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    }

    @Test
    public void whenDeletingClientWithAuthenticationToken_thenReturnSuccess() throws Exception {
        MvcResult response = mockMvc.perform(delete("/api/clients/1")
            .header("Authorization", "Bearer " + testToken)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
    }
}


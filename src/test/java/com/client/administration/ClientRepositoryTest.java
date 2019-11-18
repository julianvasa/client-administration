package com.client.administration;

import com.client.administration.model.Address;
import com.client.administration.model.Client;
import com.client.administration.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void persistUser(){
        Address ADDRESS = Address.builder().street("Street").city("City").country("Country").zip(1000).build();

        Client client = Client.builder()
        .firstName("FirstName")
        .lastName("LastName")
        .email("user@test.com")
        .address(ADDRESS).build();

        testEntityManager.persist(client);
        assertNotNull(clientRepository.findById(1L));
    }

}

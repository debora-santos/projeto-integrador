package org.generation.brazil.ecommerce.cliente;

import org.generation.brazil.ecommerce.EcommerceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Collections;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1/" + path;
    }

    private String token;

    @Before
    public void init() {
        this.token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTY0ODYzMjY4LCJleHAiOjE1NjU3MjcyNjh9.Ux6R6_WJhbMqB8dF8VP13ZlYAzK9FjpuQShGl8gOGTFNLsWuVS6nZzhQNTm7LkzZ5fQhlTi2B4_O1gHjYde3vw";
    }

    @Test
    public void save() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);

        HttpEntity<Cliente> entity = new HttpEntity<>(ClienteMock.getClienteMock(), headers);

        ResponseEntity<Cliente> responseEntity = testRestTemplate.exchange(
                getRootUrl("/clientes/"),
                HttpMethod.POST,
                entity,
                Cliente.class
        );

        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(
                getRootUrl("/clientes/"),
                HttpMethod.GET,
                entity,
                String.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.token);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Cliente> response = testRestTemplate.exchange(
                getRootUrl("/clientes/1"),
                HttpMethod.GET,
                entity,
                Cliente.class);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Bearer " + this.token);

        HttpEntity<Cliente> entity = new HttpEntity<>(ClienteMock.getClienteMock(), headers);

        ResponseEntity<Cliente> responseEntity = testRestTemplate.exchange(
                getRootUrl("/clientes/1"),
                HttpMethod.PUT,
                entity,
                Cliente.class
        );

        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void delete() {
        int id = 1;
        Cliente cliente = testRestTemplate.getForObject(getRootUrl("/clientes/delete/" + id), Cliente.class);
        assertNotNull(cliente);
        testRestTemplate.delete(getRootUrl("/clientes/delete/" + id));
        try {
            cliente = testRestTemplate.getForObject(getRootUrl("/clientes/delete/" + id), Cliente.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}

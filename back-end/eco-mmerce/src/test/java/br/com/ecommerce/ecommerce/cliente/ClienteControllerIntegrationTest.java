package br.com.ecommerce.ecommerce.cliente;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Cliente.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1/" + path;
    }

    @Test
    public void save() {

        ResponseEntity<Cliente> postResponse = testRestTemplate.postForEntity(getRootUrl("/cliente"), ClienteMock.getClienteMock(), Cliente.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/clientes"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        int id = 1;
        Cliente cliente = testRestTemplate.getForObject(getRootUrl("/clientes/" + id), Cliente.class);
        assertNotNull(cliente);

    }

    @Test
    public void update() {
        int id = 1;
        testRestTemplate.put(getRootUrl("/cliente/" + id), ClienteMock.getClienteMock());
        Cliente clienteAtualizado = testRestTemplate.getForObject(getRootUrl("/clientes/" + id), Cliente.class);
        assertNotNull(clienteAtualizado);
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


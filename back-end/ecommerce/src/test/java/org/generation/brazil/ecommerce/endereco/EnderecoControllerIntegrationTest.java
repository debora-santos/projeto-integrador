package org.generation.brazil.ecommerce.endereco;

import org.generation.brazil.ecommerce.EcommerceApplication;
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
@SpringBootTest(classes = EcommerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnderecoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1/" + path;
    }

    @Test
    public void save() {

        ResponseEntity<Endereco> postResponse = testRestTemplate.postForEntity(getRootUrl("/enderecos"), EnderecoMock.getEnderecoMock(), Endereco.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/enderecos"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        int id = 1;
        Endereco endereco = testRestTemplate.getForObject(getRootUrl("/enderecos/" + id), Endereco.class);
        assertNotNull(endereco);

    }

    @Test
    public void update() {
        int id = 1;
        testRestTemplate.put(getRootUrl("/pedidos/" + id), EnderecoMock.getEnderecoMock());
        Endereco enderecoAtualizado = testRestTemplate.getForObject(getRootUrl("/enderecos/" + id), Endereco.class);
        assertNotNull(enderecoAtualizado);
    }

    @Test
    public void delete() {
        int id = 1;
        Endereco endereco = testRestTemplate.getForObject(getRootUrl("/enderecos/delete/" + id), Endereco.class);
        assertNotNull(endereco);
        testRestTemplate.delete(getRootUrl("/enderecos/delete/" + id));
        try {
            endereco = testRestTemplate.getForObject(getRootUrl("/enderecos/delete/" + id), Endereco.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}


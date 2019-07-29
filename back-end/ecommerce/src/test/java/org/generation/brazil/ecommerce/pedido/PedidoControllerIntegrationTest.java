package org.generation.brazil.ecommerce.pedido;

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
public class PedidoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1/" + path;
    }

    @Test
    public void save() {

        ResponseEntity<Pedido> postResponse = testRestTemplate.postForEntity(getRootUrl("/pedidos"), PedidoMock.getPedidoMock(), Pedido.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/pedidos"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        int id = 1;
        Pedido pedido = testRestTemplate.getForObject(getRootUrl("/pedidos/" + id), Pedido.class);
        assertNotNull(pedido);

    }

    @Test
    public void update() {
        int id = 1;
        testRestTemplate.put(getRootUrl("/pedidos/" + id), PedidoMock.getPedidoMock());
        Pedido pedidoAtualizado = testRestTemplate.getForObject(getRootUrl("/pedidos/" + id), Pedido.class);
        assertNotNull(pedidoAtualizado);
    }

    @Test
    public void delete() {
        int id = 1;
        Pedido pedido = testRestTemplate.getForObject(getRootUrl("/pedidos/delete/" + id), Pedido.class);
        assertNotNull(pedido);
        testRestTemplate.delete(getRootUrl("/pedidos/delete/" + id));
        try {
            pedido = testRestTemplate.getForObject(getRootUrl("/pedidos/delete/" + id), Pedido.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}


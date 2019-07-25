package br.com.ecommerce.ecommerce.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import br.com.ecommerce.ecommerce.EcoMmerceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcoMmerceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PedidoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;
    private Object String;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1/pedidos/";
    }

    @Test
    public void testaCriacaoDeUmNovoPedido() {
        ResponseEntity<Pedido> postResponse =
                testRestTemplate.postForEntity(
                        getRootUrl(),
                        PedidoMock.getPedidoMock(),
                        Pedido.class);
        assertNotNull(postResponse);
        assertEquals(201,
                postResponse.getStatusCodeValue());
    }

    @Test
    public void testaConsultaDeTodosOsPedidos() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(
                getRootUrl(),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testaConsultaPorId() {
        Pedido pedido = testRestTemplate.getForObject(
                getRootUrl() + "1",
                 Pedido.class);
        System.out.println(pedido.getNumeroPedido());
        assertNotNull(pedido);
    }

    @Test
    public void testaAtualizacaoDeUmPedido() {
        int id = 1;
        Pedido pedido = testRestTemplate.getForObject(
                getRootUrl() + id,
                Pedido.class);
        String novoNumeroPedido = PedidoMock.getPedidoMock().getNumeroPedido();
        BigDecimal novoPreco = PedidoMock.getPedidoMock().getPreco();
        BigDecimal novoFrete = PedidoMock.getPedidoMock().getFrete();
        pedido.setNumeroPedido(novoNumeroPedido);
        pedido.setPreco(novoPreco);
        pedido.setFrete(novoFrete);
        testRestTemplate.put(getRootUrl() + id, pedido);
        Pedido pedidoAtualizado = testRestTemplate.getForObject(
                getRootUrl() + id,
                Pedido.class);
        assertNotNull(pedidoAtualizado);
        assertEquals (novoNumeroPedido, pedidoAtualizado.getNumeroPedido());
        assertEquals(novoPreco, pedidoAtualizado.getPreco());
        assertEquals(novoFrete, pedidoAtualizado.getFrete());
    }

    @Test
    public void testaDelecaoDeUmPedido() {
        int id = 2;
        Pedido pedido = testRestTemplate.getForObject(
                getRootUrl() + id,
                Pedido.class);
        assertNotNull(pedido);
        testRestTemplate.delete(getRootUrl() + id);
        try {
            testRestTemplate.getForObject(
                    getRootUrl() + id,
                    Pedido.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }

}
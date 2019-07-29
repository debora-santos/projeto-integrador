package br.com.ecommerce.ecommerce.produto;

/*
import br.com.ecommerce.ecommerce.EcoMmerceApplication;
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
@SpringBootTest(classes = EcoMmerceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1" + path;
    }

    @Test
    public void save() {

        ResponseEntity<Produto> postResponse = testRestTemplate.postForEntity(getRootUrl("/produto"), ProdutoMock.getProdutoMock(), Produto.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/produtos"), HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        int id = 1;
        Produto produto = testRestTemplate.getForObject(getRootUrl("/produto/" + id), Produto.class);
        assertNotNull(produto);

    }

    @Test
    public void update() {
        int id = 1;
        testRestTemplate.put(getRootUrl("/produto/" + id), ProdutoMock.getProdutoMock());
        Produto produtoAtualizado = testRestTemplate.getForObject(getRootUrl("/produto/" + id), Produto.class);
        assertNotNull(produtoAtualizado);
    }

    @Test
    public void delete() {
        int id = 2;
        Produto produto = testRestTemplate.getForObject(getRootUrl("/produto/delete/" + id), Produto.class);
        assertNotNull(produto);
        testRestTemplate.delete(getRootUrl("/produto/delete/" + id));
        try {
            produto = testRestTemplate.getForObject(getRootUrl("/produto/delete/" + id), Produto.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
*/

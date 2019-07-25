package br.com.ecommerce.ecommerce.usuario;

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
@SpringBootTest(classes = Usuario.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl(String path) {
        return "http://localhost:" + port + "/api/v1.1/" + path;
    }

    @Test
    public void save() {

        ResponseEntity<Usuario> postResponse = testRestTemplate.postForEntity(getRootUrl("/usuario"),
                UsuarioMock.getUsuarioMock(), Usuario.class);
        assertNotNull(postResponse);
        assertEquals(201, postResponse.getStatusCodeValue());

    }

    @Test
    public void findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl("/usuario"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        int id = 1;
            Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuario/" + id), Usuario.class);
        assertNotNull(usuario);

    }

    @Test
    public void update() {
        int id = 1;
        testRestTemplate.put(getRootUrl("/usuario/" + id), UsuarioMock.getUsuarioMock());
        Usuario usuarioAtualizado = testRestTemplate.getForObject(getRootUrl("/usuario/" + id), Usuario.class);
        assertNotNull(usuarioAtualizado);
    }

    @Test
    public void delete() {
        int id = 2; // Turn this value dynamic, fetch first row only ? create-drop?
        Usuario usuario = testRestTemplate.getForObject(getRootUrl("/usuario/delete/" + id), Usuario.class);
        assertNotNull(usuario);
        testRestTemplate.delete(getRootUrl("/usuario/delete/" + id));
        try {
            usuario = testRestTemplate.getForObject(getRootUrl("/usuario/delete/" + id), Usuario.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}

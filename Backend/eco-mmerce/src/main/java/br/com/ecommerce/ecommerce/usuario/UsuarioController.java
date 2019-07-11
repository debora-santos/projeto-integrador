package br.com.ecommerce.ecommerce.usuario;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/usuario")
  public Usuario save(@RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @GetMapping("/usuario")
  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  @GetMapping("/usuario/{id}")
  public Optional<Usuario> findById(@PathVariable Long id) throws ResourceNotFoundException {
    if (usuarioRepository.findById(id).isPresent()) {
      return usuarioRepository.findById(id);
    } else throw new ResourceNotFoundException("Não há produto com o id " + id);
  }

  @PutMapping("/usuario/{id}")
  public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
    return usuarioRepository.findById(id).map(usuarioAtualizado -> {
      usuarioAtualizado.setLogin(usuario.getLogin());
      usuarioAtualizado.setPassword(usuario.getPassword());
      return usuarioRepository.save(usuarioAtualizado);
    }).orElseThrow(() ->
        new ResourceNotFoundException("Não há produto com o id " + id));

  }

  @DeleteMapping("/usuario/delete")
  public void delete(@RequestParam Long id){
    usuarioRepository.deleteById(id);
  }

}

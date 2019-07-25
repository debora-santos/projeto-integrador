package br.com.ecommerce.ecommerce.usuario;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @ApiOperation(value = "Insere um novo usuário",
          notes = "Insere um novo usuário",
          response = Usuario.class)
  @ApiResponses(value = {
          @ApiResponse(code = 201, message = "Usuário inserido com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proíbido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/usuario")
  public Usuario save(@RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @ApiOperation(value = "Lista todos os usuários",
          notes = "Lista todos os usuários",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuários listados com Sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proíbido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @GetMapping("/usuario")
  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }

  @ApiOperation(value = "Lista um usuário específico",
          notes = "Lista usuário específico",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário listado com Sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proíbido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @GetMapping("/usuario/{id}")
  public Optional<Usuario> findById(@PathVariable Long id) throws ResourceNotFoundException {
    if (usuarioRepository.findById(id).isPresent()) {
      return usuarioRepository.findById(id);
    } else throw new ResourceNotFoundException("Não há usuário registrado com o id " + id);
  }

  @ApiOperation(value = "Atualiza os dados do usuário",
          notes = "Atualiza os dados do usuário",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário atualizado com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proíbido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @PutMapping("/usuario/{id}")
  public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) throws ResourceNotFoundException {
    return usuarioRepository.findById(id).map(usuarioAtualizado -> {
      usuarioAtualizado.setLogin(usuario.getLogin());
      usuarioAtualizado.setPassword(usuario.getPassword());
      return usuarioRepository.save(usuarioAtualizado);
    }).orElseThrow(() ->
        new ResourceNotFoundException("Não há usuário registrado com o id " + id));

  }

  @ApiOperation(value = "Deleta um usuário",
          notes = "Deleta um usuário",
          response = List.class)
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Usuário deletado com sucesso!"),
          @ApiResponse(code = 401, message = "Sem autorização"),
          @ApiResponse(code = 403, message = "Proíbido"),
          @ApiResponse(code = 404, message = "Não encontrado")
  })
  @DeleteMapping("/usuario/delete")
  public void delete(@RequestParam Long id){
    usuarioRepository.deleteById(id);
  }

}

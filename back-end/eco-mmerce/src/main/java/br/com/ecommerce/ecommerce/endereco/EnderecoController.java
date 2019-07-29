package br.com.ecommerce.ecommerce.endereco;

import br.com.ecommerce.ecommerce.cliente.Cliente;
import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EnderecoController {

    @Autowired
    EnderecoRepository enderecoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/enderecos")
    public Endereco save(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @GetMapping("/enderecos")
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/enderecos/{id}")
    public Optional<Endereco> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (enderecoRepository.findById(id).isPresent()) {
            return enderecoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há endereço com o id " + id);
    }

    @PutMapping("/enderecos/{id}")
    public Endereco update(@PathVariable Long id, @RequestBody Endereco endereco) throws ResourceNotFoundException{
        return  enderecoRepository.findById(id).map(enderecoAtualizado -> {
            enderecoAtualizado.setLogradouro(endereco.getLogradouro());
            enderecoAtualizado.setNumero(endereco.getNumero());
            enderecoAtualizado.setCep(endereco.getCep());
            enderecoAtualizado.setBairro(endereco.getBairro());
            enderecoAtualizado.setCidade(endereco.getCidade());
            enderecoAtualizado.setEstado(endereco.getEstado());
            enderecoAtualizado.setComplemento(endereco.getComplemento());
            return enderecoRepository.save(enderecoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há endereço com o id " +id));
    }

    @DeleteMapping("/enderecos/delete/{id}")
    public void delete(@PathVariable Long id){
        enderecoRepository.deleteById(id);
    }
}

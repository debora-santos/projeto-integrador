package br.com.ecommerce.ecommerce.endereco;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/endereco")
    public Endereco save(@RequestBody Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    @GetMapping("/endereco")
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @GetMapping("/endereco/{id}")
    public Optional<Endereco> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (enderecoRepository.findById(id).isPresent()) {
            return enderecoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há endereço com o id " + id);
    }

    @PutMapping("/endereco/{id}")
    public Endereco update(@PathVariable Long id, @RequestBody Endereco endereco) throws ResourceNotFoundException{
        return  enderecoRepository.findById(id).map(enderecoAtualizado -> {
            enderecoAtualizado.setLogradouro(endereco.getLogradouro());
            enderecoAtualizado.setNumero(endereco.getNumero());
            enderecoAtualizado.setCep(endereco.getCep());
            enderecoAtualizado.setBairro(endereco.getBairro());
            enderecoAtualizado.setCidade(endereco.getCidade());
            enderecoAtualizado.setEstado(endereco.getEstado());
            enderecoAtualizado.setComplemento(endereco.getComplemento());
            enderecoAtualizado.setReferencia(endereco.getReferencia());
            return enderecoRepository.save(enderecoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há endereco com o id " + id));
    }

    @DeleteMapping("/endereco/delete")
    public void delete(@RequestParam Long id){
        enderecoRepository.deleteById(id);
    }

}

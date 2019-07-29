package org.generation.brazil.ecommerce.cliente;

import org.generation.brazil.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (clienteRepository.findById(id).isPresent()) {
            return clienteRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há cliente com o id " + id);
    }

    @PutMapping("/clientes/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) throws ResourceNotFoundException{
        return  clienteRepository.findById(id).map(clienteAtualizado -> {
            clienteAtualizado.setIdDadosPessoais(cliente.getIdDadosPessoais());
            clienteAtualizado.setIdEndereco(cliente.getIdEndereco());
            clienteAtualizado.setIdPedido(cliente.getIdPedido());
            return clienteRepository.save(clienteAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há cliente com o id " +id));
    }

    @DeleteMapping("/clientes/delete/{id}")
    public void delete(@PathVariable Long id){
        clienteRepository.deleteById(id);
    }
}

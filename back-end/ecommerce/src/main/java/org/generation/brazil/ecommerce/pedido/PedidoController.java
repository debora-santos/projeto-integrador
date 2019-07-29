package org.generation.brazil.ecommerce.pedido;

import org.generation.brazil.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pedidos")
    public Pedido save(@RequestBody Pedido cliente) {
        return pedidoRepository.save(cliente);
    }

    @GetMapping("/pedidos")
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> findById(@PathVariable Long id) throws ResourceNotFoundException {
        if (pedidoRepository.findById(id).isPresent()) {
            return pedidoRepository.findById(id);
        } else throw new ResourceNotFoundException("Não há pedido com o id " + id);
    }

    @PutMapping("/pedidos/{id}")
    public Pedido update(@PathVariable Long id, @RequestBody Pedido pedido) throws ResourceNotFoundException{
        return  pedidoRepository.findById(id).map(pedidoAtualizado -> {
            pedidoAtualizado.setPrecoTotal(pedido.getPrecoTotal());
            pedidoAtualizado.setFrete(pedido.getFrete());
            pedidoAtualizado.setIdProduto(pedido.getIdProduto());
            pedidoAtualizado.setNumeroPedido(pedido.getNumeroPedido());
            return pedidoRepository.save(pedidoAtualizado);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há pedido com o id " +id));
    }

    @DeleteMapping("/pedidos/delete/{id}")
    public void delete(@PathVariable Long id){
        pedidoRepository.deleteById(id);
    }
}

package br.com.ecommerce.ecommerce.pedido;

import br.com.ecommerce.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/pedidos")
    public Pedido save(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @GetMapping("/pedidos")
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/pedidos/{id}")
    public Optional<Pedido> findByid(@PathVariable Long id) {
        return pedidoRepository.findById(id);
    }


    @GetMapping("/pedidos/numero/{numero}")
    public Pedido findByNumeroPedido(@PathVariable String numeroPedido) {
        return (Pedido) pedidoRepository.findByNumeroPedido(numeroPedido);
    }

    @PutMapping("/pedidos/id")
    public Pedido update(@PathVariable Long id, @RequestBody Pedido pedido) throws ResourceNotFoundException {
        return pedidoRepository.findById(id).map(updatedPedido -> {
            updatedPedido.setNumeroPedido(pedido.getNumeroPedido());
            updatedPedido.setPreco(pedido.getPreco());
            updatedPedido.setFrete(pedido.getFrete());
            return pedidoRepository.save(updatedPedido);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não há nenhum produto com o id: " + id));

    }

    @DeleteMapping("/pedidos/delete/{id}")
    public void delete(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
    }


}


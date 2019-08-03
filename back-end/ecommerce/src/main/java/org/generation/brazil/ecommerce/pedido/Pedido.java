package org.generation.brazil.ecommerce.pedido;

import lombok.Data;
import org.generation.brazil.ecommerce.cliente.Cliente;
import org.generation.brazil.ecommerce.produto.Produto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "preco_total")
    private BigDecimal precoTotal;

    @NotNull
    private BigDecimal frete;

    @NotNull
    @Column(name = "numero_pedido")
    private String numeroPedido;

    @NotNull
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Produto> idProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}

package br.com.ecommerce.ecommerce.pedido;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

    @Column(name = "id_produto")
    private String idProduto;


}

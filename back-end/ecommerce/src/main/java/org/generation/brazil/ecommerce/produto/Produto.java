package org.generation.brazil.ecommerce.produto;

import lombok.Data;
import org.generation.brazil.ecommerce.pedido.Pedido;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;
}

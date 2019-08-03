package org.generation.brazil.ecommerce.cliente;

import lombok.Data;
import org.generation.brazil.ecommerce.dadospessoais.DadosPessoais;
import org.generation.brazil.ecommerce.endereco.Endereco;
import org.generation.brazil.ecommerce.pedido.Pedido;
import org.generation.brazil.ecommerce.user.User;


import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    private DadosPessoais dadosPessoais;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Endereco> enderecos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}

package org.generation.brazil.ecommerce.dadospessoais;

import lombok.Data;
import lombok.Singular;
import org.generation.brazil.ecommerce.cliente.Cliente;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class DadosPessoais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 14, max = 14)
    private String cpf;

    @NotBlank
    private String telefone;

    @NotNull
    @Size(max = 1)
    @OneToOne(fetch = FetchType.LAZY)
    private Cliente cliente;
}

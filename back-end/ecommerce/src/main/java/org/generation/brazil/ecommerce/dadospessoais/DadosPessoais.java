package org.generation.brazil.ecommerce.dadospessoais;

import lombok.Data;
import lombok.Singular;

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
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Column(unique = true)
    @Size(min = 14, max = 14)
    private String cpf;

    @NotBlank
    private String telefone;

    @Email
    @NotBlank
    private String email;
}

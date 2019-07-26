package br.com.ecommerce.ecommerce.endereco;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String logradouro;

    @NotNull
    private Long numero;

    @NotNull
    private Long cep;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private Long estado;

    private Long complemento;

    private String referencia;

}

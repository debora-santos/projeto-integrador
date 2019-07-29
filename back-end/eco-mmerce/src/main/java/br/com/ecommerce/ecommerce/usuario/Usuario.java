package br.com.ecommerce.ecommerce.usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "id_cliente")
  private Long idCliente;

  @NotNull
  private String login;

  @NotNull
  private String senha;

}

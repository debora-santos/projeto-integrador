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

  //@OneToOne
  //@JoinColumn(name = "id_dados_pessoais", referencedColumnName = "id")
  @NotNull
  @Column(name = "id_dados_pessoais")
  private Long idCliente;

  @NotNull
  private String login;

  @NotNull
  private String password;

}

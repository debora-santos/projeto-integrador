package br.com.ecommerce.ecommerce.usuario;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Usuario {

  @NotNull
  private String login;

  @NotNull
  private String password;

}

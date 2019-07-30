package org.generation.brazil.ecommerce.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtAuthenticationResponse {

  private String accessToken;
  private String tokenType;

}

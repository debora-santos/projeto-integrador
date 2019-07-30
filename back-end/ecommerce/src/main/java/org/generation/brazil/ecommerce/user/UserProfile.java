package org.generation.brazil.ecommerce.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfile {

  private Long id;
  private String username;
  private String name;

}
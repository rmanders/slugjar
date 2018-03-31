package org.schlocknet.slugjar.model.auth;

import lombok.Data;

/**
 * Represents a login request for a user
 */
@Data
public class LoginRequest {

  /** The username of the user */
  private String username;

  /** The user's password */
  private String password;
}

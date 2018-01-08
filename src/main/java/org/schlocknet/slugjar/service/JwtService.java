package org.schlocknet.slugjar.service;

import java.util.List;

/**
 * Service definition for JSON web tokens
 */
public interface JwtService {


  /**
   * Creates an JSON Web Token signed by the server
   *
   * @param username The username of the user
   *
   * @param roles A list of roles this user has
   *
   * @return A string representing the JSON Web Token
   */
  String createJwt(String username, List<String> roles);

  /**
   * Determines if the JSON Web Token is valid and has a valid signature
   *
   * @param token The JSOn Web Token to validate
   *
   * @return true if the token was valid, false otherwise
   */
  boolean jwtIsValid(String token);
}

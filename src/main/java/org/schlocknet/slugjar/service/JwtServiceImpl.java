package org.schlocknet.slugjar.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Service implementation for JSON Web Tokens
 */
public class JwtServiceImpl implements JwtService{

  /**
   * Local logger instance
   * */
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);

  public static final String JWT_ISSUER = "slugjar";

  public static final String JWT_AUDIENCE = "user";

  public static final String JWT_CLAIM_USERNAME = "slugjarUsername";

  public static final String JWT_CLAIM_ROLES = "slugjarRoles";

  public static final long JWT_EXPIRE_TIME = 1000L * 60L * 60L * 24L;


  /**
   * The algorithm used to sign the JSON Web Token
   */
  private final Algorithm jwtAlgorithm;

  /**
   * Object used to verify JSON Web Token
   */
  private final JWTVerifier jwtVerifier;

  /**
   * Default constructor
   *
   * @param secret The server secret used to sign the JSON Web Token with
   */
  public JwtServiceImpl(String secret) {
    if (StringUtils.isBlank(secret)) {
      throw new IllegalStateException("JSON Web Token secret cannot be empty or null");
    }
    else if (secret.length() < 16) {
      throw new IllegalStateException("JSON Web Token secret cannot  be less than 16 characters");
    }

    try {
      // Choose the Signing algorithm and set the JWT secret
      this.jwtAlgorithm = Algorithm.HMAC256(secret);

      // Create the JSON Web Token verifier instance
      this.jwtVerifier = JWT
          .require(this.jwtAlgorithm)
          .withIssuer(JWT_ISSUER)
          .build();
    }
    catch (UnsupportedEncodingException ex) {
      final String errorMsg = "There was an encoding error while trying to set the JSON Web Token secret";
      LOGGER.error(errorMsg, ex);
      throw new IllegalStateException(errorMsg, ex);
    }
  }

  /**
   *
   * @param algorithm
   */
  public JwtServiceImpl(Algorithm algorithm) {
    if (algorithm == null) {
      throw new IllegalStateException("JSON Web Token signing algorithm cannot be null");
    }
    this.jwtAlgorithm = algorithm;

    // Create the JSON Web Token verifier instance
    this.jwtVerifier = JWT
        .require(this.jwtAlgorithm)
        .withIssuer(JWT_ISSUER)
        .build();
  }

  /**
   * Creates an JSON Web Token signed by the server
   *
   * @param username The username of the user
   *
   * @param roles A list of roles this user has
   *
   * @return A string representing the JSON Web Token
   */
  @Override
  public String createJwt(String username, List<String> roles) {
    LOGGER.info("Creating JSON Web Token for user: {} with roles: {}", username, roles);

    if (StringUtils.isBlank(username)) throw new IllegalArgumentException("username parameter cannot be null or empty");

    final String[] userRoles = (roles == null ? new String[0] : roles.toArray(new String[roles.size()]));

    try {
      return JWT.create()
          .withIssuer(JWT_ISSUER)
          .withAudience(JWT_AUDIENCE)
          .withSubject(username)
          .withClaim(JWT_CLAIM_USERNAME, username)
          .withArrayClaim(JWT_CLAIM_ROLES, userRoles)
          .withExpiresAt(new Date(System.currentTimeMillis() + JWT_EXPIRE_TIME))
          .sign(jwtAlgorithm);
    } catch (JWTCreationException | IllegalArgumentException ex) {
      LOGGER.error("Error creating JSON Web Token for username: {}", username, ex);
      return null;
    }
  }

  /**
   * Determines if the JSON Web Token is valid and has a valid signature
   *
   * @param token The JSOn Web Token to validate
   *
   * @return true if the token was valid, false otherwise
   */
  @Override
  public boolean jwtIsValid(String token) {
    LOGGER.debug("Validating JSON Web Token: {}", token);

    if (StringUtils.isBlank(token)) { return false; }

    try {
      final DecodedJWT jwt = jwtVerifier.verify(token);

      final Claim claimUsername = jwt.getClaim(JWT_CLAIM_USERNAME);
      final Claim claimRoles = jwt.getClaim(JWT_CLAIM_ROLES);

      if (claimUsername.isNull() || StringUtils.isBlank(claimUsername.asString())) {
        LOGGER.warn("Got Invalid JSON Web Token: {}, username cannot be null or blank", token);
        return false;
      }
      else if (claimRoles.isNull() || claimRoles.asList(String.class).isEmpty()) {
        LOGGER.warn("Got Invalid JSON Web Token: {}, roles cannot be null or empty", token);
        return false;
      }
    } catch (JWTVerificationException ex) {
      LOGGER.warn("Got invalid JSON Web Token: {}", token);
      return false;
    }
    LOGGER.debug("JSON Web Token was successfully verified");
    return true;
  }
}

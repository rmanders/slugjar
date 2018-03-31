package org.schlocknet.slugjar.controller;

import org.schlocknet.slugjar.model.UserInfo;
import org.schlocknet.slugjar.model.auth.LoginRequest;
import org.schlocknet.slugjar.model.response.ApiResponse;
import org.schlocknet.slugjar.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Rest controller for authenticating users
 */
@RestController
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthenticationController {

  /** Local logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

  /** Provides acces to Jwt Operations */
  private final JwtService jwtService;

  /**
   * Default constructor
   */
  @Autowired
  public AuthenticationController(JwtService jwtService) {
    if (jwtService == null) {
      throw new IllegalArgumentException("Argument: \"jwtService\" cannot be null");
    }
    this.jwtService = jwtService;
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<ApiResponse> doLogin(@RequestBody LoginRequest loginRequest) {
    LOGGER.debug("Got request for: /api/auth/login");
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}

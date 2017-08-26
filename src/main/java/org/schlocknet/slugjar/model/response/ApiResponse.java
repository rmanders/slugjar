package org.schlocknet.slugjar.model.response;

import lombok.Data;

@Data
public class ApiResponse {

  private final String status;

  private final String message;


  public ApiResponse(String status) {
    this.status = status;
    this.message = null;
  }

  public ApiResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

}

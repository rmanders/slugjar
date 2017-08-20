package org.schlocknet.slugjar.model.response;

import lombok.Data;

@Data
public class ApiResponse<T> {

  private final String status;

  private final String message;

  private T responseObject = null;

  public ApiResponse(String status) {
    this.status = status;
    this.message = null;
  }

  public ApiResponse(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public ApiResponse(String status, String message, T reponseObject) {
    this.status = status;
    this.message = message;
    this.responseObject = reponseObject;
  }
}

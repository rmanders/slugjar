package org.schlocknet.slugjar.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Custom result object from REST API
 */
@Data
public class ApiResponse {

  /** A brief indication of the state the request (i.e. 'success', 'failure') */
  @ApiModelProperty(
      value = "A brief indication of the state the request (i.e. 'success', 'failure')",
      required=true
  )
  private ApiResponseStatus status;

  /** A brief (optional) message providing more details regarding the response status */
  @ApiModelProperty(
      value = "A brief (optional) message providing more details regarding the response status",
      position = 1
  )
  private String message;

  /**
   * Indicates the http status that a controller should return with this response
   */
  @JsonIgnore
  private HttpStatus httpStatus = HttpStatus.OK;

  /**
   * Constructor for just setting the status field
   * @param status
   */
  public ApiResponse(ApiResponseStatus status) {
    this.status = status;
    this.message = null;
  }

  /**
   * Constructor for setting the status and message fields
   * @param status - member of the ApiResponseStatus enum
   * @param message Explanation message
   */
  public ApiResponse(ApiResponseStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  /**
   * Constructor for setting the status, message and HttpStatus fields
   * @param status member of the ApiResponseStatus enum
   * @param message Explanation message
   * @param httpStatus member of the HttpStatus enum
   */
  public ApiResponse(ApiResponseStatus status, String message, HttpStatus httpStatus) {
    this.status = status;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  /** Creates a new instance of ApiResponse with status set to 'failed' and message set to passed in value */
  public static ApiResponse failFactory(String failMessage) {
    return new ApiResponse(ApiResponseStatus.FAILED, failMessage, HttpStatus.BAD_REQUEST);
  }

  /** Creates a new instance of ApiResponse with status set to 'failed' and message set to passed in value */
  public static ApiResponse failFactory(String failMessage, HttpStatus httpStatus) {
    return new ApiResponse(ApiResponseStatus.FAILED, failMessage, httpStatus);
  }
}

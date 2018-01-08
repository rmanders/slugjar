package org.schlocknet.slugjar.model.response;

/**
 * Enum defining all possible response statuses
 */
public enum ApiResponseStatus {

  SUCCEEDED("succeeded"),
  FAILED("failed");

  private final String value;
  ApiResponseStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public String getValue() { return toString(); }
}

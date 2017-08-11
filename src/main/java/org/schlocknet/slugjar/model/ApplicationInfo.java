package org.schlocknet.slugjar.model;

import lombok.Data;

/**
 * POJO containing info about the application
 */
@Data
public class ApplicationInfo {

  /** The environment we're running in: development, production etc. */
  private final String fleet;

  /** The name of this application */
  private final String applicationName;

  /** The version of this application */
  private final String applicationVerson;

  /** The date this application instance was built */
  private final String buildDate;

  /**
   * Default constructor
   * @param fleet
   * @param applicationName
   * @param applicationVerson
   * @param buildDate
   */
  public ApplicationInfo(String fleet, String applicationName, String applicationVerson, String buildDate) {
    this.fleet = fleet;
    this.applicationName = applicationName;
    this.applicationVerson = applicationVerson;
    this.buildDate = buildDate;
  }
}

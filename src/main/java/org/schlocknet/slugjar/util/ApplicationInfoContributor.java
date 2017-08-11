package org.schlocknet.slugjar.util;

import lombok.Getter;
import org.schlocknet.slugjar.model.ApplicationInfo;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.HashMap;
import java.util.Map;

/**
 * Contributes Application information through the Spring Actuator API
 */
public class ApplicationInfoContributor implements InfoContributor {

  /** Information about the running application */
  @Getter
  private final ApplicationInfo applicationInfo;

  /**
   * Default constructor
   *
   * @param applicationInfo Information about the running application
   */
  public ApplicationInfoContributor(ApplicationInfo applicationInfo) {
    if (applicationInfo == null) {
      throw new IllegalArgumentException("The ApplicationInfo parameter cannto be null");
    }
    this.applicationInfo = applicationInfo;
  }

  /**
   * Obtain the application information from the Java manifest and return it to the actuator API
   *
   * @param builder
   */
  @Override
  public void contribute(Info.Builder builder) {
    final Map<String, Object> appInfo = new HashMap<>();
    appInfo.put("applicationName", applicationInfo.getApplicationName());
    appInfo.put("version", applicationInfo.getApplicationVerson());
    appInfo.put("fleet", applicationInfo.getFleet());
    appInfo.put("buildDate", applicationInfo.getBuildDate());

    builder.withDetails(appInfo);
  }
}

package org.schlocknet.slugjar.util;

import org.schlocknet.slugjar.model.ApplicationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * Utility class used to provide information about the application's environment
 */
public class ApplicationEnvironment {

  /** Local logger */
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationEnvironment.class);

  /** Default value for VersionInfo object fields */
  public static final String UNKNOWN = "unknown";

  /** The system property which contains the active fleet/environment */
  public static final String FLEET_PROP = "spring.profiles.active";

  /**
   * Default constructor
   */
  private ApplicationEnvironment() {
    // Do Nothing
  }

  /**
   * Attempts to determine which fleet/environment the calling application is running in. If no information can
   * be found, it sets the environment to "dev" by default.
   */
  public static void initializeFleet() {

    // Check for and set the environment (fleet) we ar running in
    String fleet = System.getProperty(FLEET_PROP, System.getenv("FLEET"));
    if(fleet == null) {
      System.out.println("No [FLEET] or [spring.profiles.active] value was found. Defaulting to spring.profiles.active=dev");
      fleet = "dev";
    }
    System.out.println("Executing in environment: " + fleet);
    System.setProperty(FLEET_PROP, fleet);
  }

  /**
   *
   * Given a class, attempts to extract specific information from the jar manifest for the jar that class resides in.
   *
   * @param clazz
   *
   * @return
   */
  public static ApplicationInfo loadApplicationInfoFromManifest(Class clazz) {
    final String classPath = clazz.getResource(clazz.getSimpleName() + ".class").toString();

    if (!classPath.startsWith("jar")) {
      // Class not from JAR
      LOG.error("Class: [{}] is not from a jar. Unable to load manifest", clazz);
      return new ApplicationInfo(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);
    }
    final String manifestPath = classPath.substring(0, classPath.indexOf('!') + 1) +
        "/META-INF/MANIFEST.MF";

    Manifest manifest = null;
    try {
      manifest = new Manifest(new URL(manifestPath).openStream());
    } catch (IOException ex) {
      LOG.error("Unable to load jar manifest for classPath {} with manifest: {}", classPath, manifestPath, ex);
      return new ApplicationInfo(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN);
    }

    Attributes attr = manifest.getMainAttributes();
    return new ApplicationInfo(
        System.getProperty(FLEET_PROP, "unknown"),
        attr.getValue("Implementation-Title"),
        attr.getValue("Implementation-Version"),
        attr.getValue("Build-Date"));
  }

}

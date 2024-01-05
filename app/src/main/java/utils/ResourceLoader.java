package utils;

import java.io.InputStream;
import java.net.URL;

/**
 * The ResourceLoader class provides utility methods for loading resources from the classpath.
 */
public class ResourceLoader {
  private ResourceLoader() {}

  /**
   * Loads a resource from the classpath and returns its URL.
   *
   * @param path the path of the resource
   * @return the URL of the resource
   */
  public static URL loadURL(String path) {
    return ResourceLoader.class.getResource(path);
  }

  /**
   * Loads a resource from the classpath and returns its string representation.
   *
   * @param path the path of the resource
   * @return the string representation of the resource
   */
  public static String load(String path) {
    return loadURL(path).toString();
  }

  /**
   * Loads a resource from the classpath and returns its input stream.
   *
   * @param name the name of the resource
   * @return the input stream of the resource
   */
  public static InputStream loadStream(String name) {
    return ResourceLoader.class.getResourceAsStream(name);
  }
}

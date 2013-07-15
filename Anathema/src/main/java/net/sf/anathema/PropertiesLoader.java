package net.sf.anathema;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


/**
 * This was originally de.idos.updates.PropertiesLoader from IDOS' project "updates-r-simple" (github.com/urskr/updates-r-simple).
 */
public class PropertiesLoader {
  private String propertiesName;

  public PropertiesLoader(String propertiesName) {
    this.propertiesName = propertiesName;
  }

  public Properties load() {
    try {
      Properties properties = new Properties();
      fillPropertiesFromClassPath(properties);
      fillPropertiesFromFileSystem(properties);
      if (properties.isEmpty()) {
        throw new ConfigurationFailedException(
                "Could not find configuration file " + propertiesName + " on either classpath or filesystem.");
      }
      return properties;
    } catch (ConfigurationFailedException e) {
      throw e;
    } catch (Exception e) {
      throw new ConfigurationFailedException(e);
    }
  }

  private void fillPropertiesFromClassPath(Properties properties) throws IOException {
    InputStream result = getStreamFromClassPath(propertiesName);
    if (result != null) {
      fillPropertiesFromStream(properties, result);
    }
  }

  private void fillPropertiesFromFileSystem(Properties properties) throws IOException {
    Path propertiesFile = Paths.get(propertiesName);
    if (Files.exists(propertiesFile)) {
      try (InputStream stream = Files.newInputStream(propertiesFile)) {
        fillPropertiesFromStream(properties, stream);
      }
    }
  }

  private void fillPropertiesFromStream(Properties properties, InputStream result) throws IOException {
    properties.load(result);
    result.close();
  }

  private InputStream getStreamFromClassPath(String path) {
    InputStream result;
    result = getClass().getClassLoader().getResourceAsStream(path);
    return result;
  }
}
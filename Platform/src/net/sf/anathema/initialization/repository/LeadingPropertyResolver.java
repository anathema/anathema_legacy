package net.sf.anathema.initialization.repository;

import java.util.Properties;

public class LeadingPropertyResolver {

  private final Properties properties;
  private final String wildcard;
  private final String propertyName;

  public LeadingPropertyResolver(Properties properties, String wildcard, String propertyName) {
    this.properties = properties;
    this.wildcard = wildcard;
    this.propertyName = propertyName;
  }

  public String parse(String path) {
    if (path.startsWith(wildcard)) {
      String postfix = path.substring(wildcard.length());
      return properties.getProperty(propertyName) + postfix;
    }
    return path;
  }
}
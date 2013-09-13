package net.sf.anathema.framework.environment.preferences;

import de.idos.updates.configuration.ConfigurationFailedException;
import de.idos.updates.configuration.PropertiesLoader;
import net.sf.anathema.framework.environment.Preferences;

import java.util.Properties;

public class PropertyPreferences implements Preferences {
  public static final String PREFERENCES_PROPERTIES = "preferences.properties";
  
  @Override
  public String getPreference(String key) {
    try {
      Properties properties = new PropertiesLoader(PREFERENCES_PROPERTIES).load();
      return properties.getProperty(key);
    } catch (ConfigurationFailedException e) {
      return PREFERENCE_NOT_SET;
    }
  }
}
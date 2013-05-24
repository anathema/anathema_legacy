package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.configuration.IInitializationPreferences;

import java.util.Locale;

public class LocaleEnvironment {

  public static void initLocale(IInitializationPreferences preferences) {
    Locale.setDefault(preferences.getPreferredLocale());
  }
}
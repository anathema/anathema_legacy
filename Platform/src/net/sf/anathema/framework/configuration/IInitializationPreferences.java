package net.sf.anathema.framework.configuration;

import java.util.Locale;

public interface IInitializationPreferences {

  boolean initMaximized();

  String getUserLookAndFeel();

  Locale getPreferredLocale();

  int getTooltipTimePreference();

  String getRepositoryLocationPreference(String defaultrepository);
}
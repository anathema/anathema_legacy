package net.sf.anathema.framework.configuration;

import java.util.Locale;

public interface IAnathemaPreferences {

  public boolean initMaximized();

  public boolean isMetalLookAndFeelForced();

  public Locale getPreferredLocale();

  public int getTooltipTimePreference();

  public String getRepositoryLocationPreference(String defaultrepository);
}
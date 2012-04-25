package net.sf.anathema.platform.environment;

import java.util.Locale;

import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyInitializationPreferences implements IInitializationPreferences {

  private Locale locale;
  private int toolTipSeconds;
  private String userLookAndFeel;
  private String repositoryLocation;

  @Override
  public boolean initMaximized() {
    throw new NotYetImplementedException();
  }

  @Override
  public String getRepositoryLocationPreference(String defaultValue) {
    if (repositoryLocation != null) {
      return repositoryLocation;
    }
    return defaultValue;
  }

  public void setRepositoryLocationPreference(String preference) {
    this.repositoryLocation = preference;
  }

  @Override
  public String getUserLookAndFeel() {
    return userLookAndFeel;
  }

  @Override
  public Locale getPreferredLocale() {
    return locale;
  }

  @Override
  public int getTooltipTimePreference() {
    return toolTipSeconds;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  public void setToolTipTime(int toolTipSeconds) {
    this.toolTipSeconds = toolTipSeconds;
  }

  public void setUserLookAndFeel(String preference) {
    userLookAndFeel = preference;
  }
}
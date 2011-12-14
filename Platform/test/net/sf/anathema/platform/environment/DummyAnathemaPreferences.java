package net.sf.anathema.platform.environment;

import java.util.Locale;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyAnathemaPreferences implements IAnathemaPreferences {

  private Locale locale;
  private int toolTipSeconds;
  private String userLookAndFeel;
  private String repositoryLocation;

  public boolean initMaximized() {
    throw new NotYetImplementedException();
  }

  public String getRepositoryLocationPreference(String string) {
    if (string != null) {
      return string;
    }
    return repositoryLocation;
  }

  public void setRepositoryLocationPreference(String preference) {
    this.repositoryLocation = preference;
  }

  public String getUserLookAndFeel() {
    return userLookAndFeel;
  }

  public Locale getPreferredLocale() {
    return locale;
  }

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
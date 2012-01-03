package net.sf.anathema.framework.configuration;

import java.util.Locale;

public interface IAnathemaPreferences {

  public boolean initMaximized();

  /**
   * Returns the class name of the user defined Look and Feel.
   * Don't assume that the returned class name is valid.
   * 
   *  @return the class name of the user defined Look and Feel or {@code null}
   *    if it was defined by the user.
   */
  public String getUserLookAndFeel();

  public Locale getPreferredLocale();

  public int getTooltipTimePreference();

  public String getRepositoryLocationPreference(String defaultrepository);
}
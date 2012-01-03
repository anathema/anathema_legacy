package net.sf.anathema.framework.configuration;

import net.sf.anathema.framework.presenter.action.SupportedLocale;
import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;

import java.util.Locale;
import java.util.prefs.Preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.*;

public class AnathemaPreferences implements IAnathemaPreferences {

  private static final IAnathemaPreferences instance = new AnathemaPreferences(Preferences.userRoot().node(
      SYSTEM_PREFERENCES_NODE));

  private final Preferences systemPreferences;

  public AnathemaPreferences(Preferences rootPreference) {
    this.systemPreferences = rootPreference;
  }

  public static IAnathemaPreferences getDefaultPreferences() {
    return instance;
  }

  public boolean initMaximized() {
    return systemPreferences.getBoolean(MAXIMIZE_PREFERENCE, false);
  }

  public String getUserLookAndFeel() {
    return systemPreferences.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
  }

  public Locale getPreferredLocale() {
    return SupportedLocale.valueOf(
        systemPreferences.get(
            IAnathemaPreferencesConstants.LOCALE_PREFERENCE,
            IAnathemaPreferencesConstants.DEFAULT_LOCALE)).getLocale();
  }

  public int getTooltipTimePreference() {
    return systemPreferences.getInt(IAnathemaPreferencesConstants.TOOL_TIP_TIME_PREFERENCE, 10);
  }

  public String getRepositoryLocationPreference(String defaultLocation) {
    String location = systemPreferences.get(
        IAnathemaPreferencesConstants.REPOSITORY_PREFERENCE,
        IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION);
    if (IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION.equals(location) && defaultLocation != null) {
      return defaultLocation;
    }
    return location;
  }
}
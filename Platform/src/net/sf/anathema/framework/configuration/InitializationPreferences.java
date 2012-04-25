package net.sf.anathema.framework.configuration;

import net.sf.anathema.framework.presenter.action.SupportedLocale;
import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;

import java.util.Locale;
import java.util.prefs.Preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.*;

public class InitializationPreferences implements IInitializationPreferences {

  private static final IInitializationPreferences instance = new InitializationPreferences(Preferences.userRoot().node(
      SYSTEM_PREFERENCES_NODE));

  private final Preferences systemPreferences;

  public InitializationPreferences(Preferences rootPreference) {
    this.systemPreferences = rootPreference;
  }

  public static IInitializationPreferences getDefaultPreferences() {
    return instance;
  }

  @Override
  public boolean initMaximized() {
    return systemPreferences.getBoolean(MAXIMIZE_PREFERENCE, false);
  }

  @Override
  public String getUserLookAndFeel() {
    return systemPreferences.get(USER_LOOK_AND_FEEL_CLASSNAME, null);
  }

  @Override
  public Locale getPreferredLocale() {
    return SupportedLocale.valueOf(
        systemPreferences.get(
            IAnathemaPreferencesConstants.LOCALE_PREFERENCE,
            IAnathemaPreferencesConstants.DEFAULT_LOCALE)).getLocale();
  }

  @Override
  public int getTooltipTimePreference() {
    return systemPreferences.getInt(IAnathemaPreferencesConstants.TOOL_TIP_TIME_PREFERENCE, 10);
  }

  @Override
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
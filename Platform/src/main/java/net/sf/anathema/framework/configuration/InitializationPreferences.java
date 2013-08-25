package net.sf.anathema.framework.configuration;

import net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants;

import java.util.prefs.Preferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.SYSTEM_PREFERENCES_NODE;

public class InitializationPreferences implements RepositoryPreference {

  private final Preferences systemPreferences;

  public InitializationPreferences() {
    this(Preferences.userRoot().node(SYSTEM_PREFERENCES_NODE));
  }

  public InitializationPreferences(Preferences rootPreference) {
    this.systemPreferences = rootPreference;
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
package net.sf.anathema.framework.configuration;

import net.sf.anathema.framework.environment.Preferences;
import net.sf.anathema.framework.repository.preferences.RepositoryPreferenceModel;

public class PreferencesBasedRepositoryLocation implements RepositoryPreference {

  private Preferences preferences;

  public PreferencesBasedRepositoryLocation(Preferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public String getRepositoryLocationPreference() {
    String location = preferences.getPreference(RepositoryPreferenceModel.key.key);
    if (location == Preferences.PREFERENCE_NOT_SET) {
      return RepositoryPreferenceModel.DEFAULT_REPOSITORY_LOCATION;
    }
    return location;
  }
}

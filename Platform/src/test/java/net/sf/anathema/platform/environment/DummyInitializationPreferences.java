package net.sf.anathema.platform.environment;

import net.sf.anathema.framework.configuration.RepositoryPreference;

public class DummyInitializationPreferences implements RepositoryPreference {

  private String repositoryLocation;

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
}
package net.sf.anathema.initialization.repository;

import net.sf.anathema.framework.configuration.RepositoryPreference;

public class RepositoryLocationResolver implements IStringResolver {

  private final RepositoryPreference preferences;

  public RepositoryLocationResolver(RepositoryPreference preferences) {
    this.preferences = preferences;
  }

  @Override
  public String resolve() {
    return parseOutUserHome(findRepositoryLocationDescription());
  }

  private String parseOutUserHome(String directory) {
    return new LeadingPropertyResolver(System.getProperties(), "%USER_HOME%", "user.home").parse(directory);
  }

  private String findRepositoryLocationDescription() {
    return preferences.getRepositoryLocationPreference();
  }
}
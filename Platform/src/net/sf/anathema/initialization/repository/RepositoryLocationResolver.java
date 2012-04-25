package net.sf.anathema.initialization.repository;

import net.sf.anathema.framework.configuration.IInitializationPreferences;

public class RepositoryLocationResolver implements IStringResolver {

  private final IInitializationPreferences preferences;

  public RepositoryLocationResolver(IInitializationPreferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public String resolve() {
    String repositoryLocationDescription = findRepositoryLocationDescription();
    LeadingPropertyResolver resolver = new LeadingPropertyResolver(System.getProperties(), "%USER_HOME%", "user.home");
    return resolver.parse(repositoryLocationDescription);
  }

  private String findRepositoryLocationDescription() {
    String repository = System.getProperty("repository"); //$NON-NLS-1$
    if (repository != null) {
      return repository;
    }
    String defaultrepository = System.getProperty("defaultrepository"); //$NON-NLS-1$
    return preferences.getRepositoryLocationPreference(defaultrepository);
  }
}
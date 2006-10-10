package net.sf.anathema.initialization.repository;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;

public class RepositoryLocationResolver implements IStringResolver {

  private final IAnathemaPreferences preferences;

  public RepositoryLocationResolver(IAnathemaPreferences preferences) {
    this.preferences = preferences;
  }

  public String resolve() {
    String repositryLocationDescription = findRepositoryLocationDescription();
    return new LeadingPropertyResolver(System.getProperties(), "%USER_HOME%", "user.home").parse(repositryLocationDescription); //$NON-NLS-1$//$NON-NLS-2$
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
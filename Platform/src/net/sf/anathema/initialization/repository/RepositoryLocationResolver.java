package net.sf.anathema.initialization.repository;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION;

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
      String repository = getDefaultLocation();
      if( System.getProperty("repository") != null ) {
          return repository;
      }
      return preferences.getRepositoryLocationPreference(repository);
  }
  
  public String getDefaultLocation() {
      String repository = System.getProperty("repository"); //$NON-NLS-1$         // handles linux
      if( repository == null ) {
          repository = System.getProperty("defaultrepository"); //$NON-NLS-1$     // handles mac
      }
      if( repository == null ) {
          repository = DEFAULT_REPOSITORY_LOCATION; // handles everything else
      }
      return repository;
  }
}
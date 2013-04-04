package net.sf.anathema.initialization.repository;

import net.sf.anathema.framework.configuration.IInitializationPreferences;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION;

public class RepositoryLocationResolver implements IStringResolver {

  private final IInitializationPreferences preferences;

  public RepositoryLocationResolver(IInitializationPreferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public String resolve() {
    return parseOutUserHome(findRepositoryLocationDescription());
  }
  
  private String parseOutUserHome( String directory ) {
    return new LeadingPropertyResolver(System.getProperties(), "%USER_HOME%", "user.home").parse(directory);
  }

  private String findRepositoryLocationDescription() {
      String repository = getDefaultLocation();
      if( System.getProperty("repository") != null ) {
          return repository;
      }
      return preferences.getRepositoryLocationPreference(repository);
  }
  
  public String getDefaultLocation() {
      String repository = System.getProperty("repository");          // handles linux
      if( repository == null ) {
          repository = System.getProperty("defaultrepository");      // handles mac
      }
      if( repository == null ) {
          repository = DEFAULT_REPOSITORY_LOCATION;                               // handles everything else
      }
      return parseOutUserHome(repository);
  }
}
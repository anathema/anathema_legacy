package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.PreferencesBasedRepositoryLocation;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;

import java.io.File;

public class AnathemaModelInitializer {

  public IApplicationModel initializeModel(Environment environment) throws InitializationException {
    return createModel(environment);
  }

  private ApplicationModel createModel(Environment environment) throws InitializationException {
    try {
      return new ApplicationModel(createRepositoryFolder(environment), environment);
    } catch (RepositoryException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e);
    }
  }

  private File createRepositoryFolder(Environment environment) throws RepositoryException {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    RepositoryPreference preferences = new PreferencesBasedRepositoryLocation(environment);
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(preferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}
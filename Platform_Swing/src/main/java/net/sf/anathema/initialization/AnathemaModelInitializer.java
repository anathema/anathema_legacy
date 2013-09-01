package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.RepositoryPreference;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;

import java.io.File;

public class AnathemaModelInitializer {

  private final RepositoryPreference preferences;
  private final Iterable<ExtensionWithId> extensions;

  public AnathemaModelInitializer(RepositoryPreference preferences, Iterable<ExtensionWithId> extensions) {
    this.preferences = preferences;
    this.extensions = extensions;
  }

  public IApplicationModel initializeModel(Environment environment) throws InitializationException {
    ApplicationModel model = createModel(environment);
    for (ExtensionWithId extension : extensions) {
      extension.register(model, environment);
    }
    return model;
  }

  private ApplicationModel createModel(Environment environment) throws InitializationException {
    try {
      return new ApplicationModel(createRepositoryFolder(), environment);
    } catch (RepositoryException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e);
    }
  }

  private File createRepositoryFolder() throws RepositoryException {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(preferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}
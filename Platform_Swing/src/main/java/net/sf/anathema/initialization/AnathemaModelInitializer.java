package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.Resources;

import java.io.File;

public class AnathemaModelInitializer {

  private final IInitializationPreferences preferences;
  private Iterable<ExtensionWithId> extensions;

  public AnathemaModelInitializer(IInitializationPreferences preferences, Iterable<ExtensionWithId> extensions) {
    this.preferences = preferences;
    this.extensions = extensions;
  }

  public IApplicationModel initializeModel(Resources resources, ObjectFactory instantiater, ResourceLoader loader) throws InitializationException {
    ApplicationModel model = createModel(resources, loader, instantiater);
    for (ExtensionWithId extension : extensions) {
      extension.register(model, loader);
    }
    return model;
  }

  private ApplicationModel createModel(Resources resources, ResourceLoader resourceLoader, ObjectFactory instantiater) throws InitializationException {
    try {
      return new ApplicationModel(createRepositoryFolder(), resources, resourceLoader, instantiater);
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
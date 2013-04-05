package net.sf.anathema.initialization;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.reflections.AnnotationFinder;
import net.sf.anathema.initialization.reflections.ResourceLoader;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.Resources;

import java.io.File;
import java.util.Collection;

public class AnathemaModelInitializer {

  private final IInitializationPreferences preferences;
  private final Collection<IItemTypeConfiguration> itemTypeConfigurations;
  private Iterable<ExtensionWithId> extensions;

  public AnathemaModelInitializer(IInitializationPreferences preferences, Collection<IItemTypeConfiguration> itemTypeConfigurations,
                                  Iterable<ExtensionWithId> extensions) {
    this.preferences = preferences;
    this.itemTypeConfigurations = itemTypeConfigurations;
    this.extensions = extensions;
  }

  public IApplicationModel initializeModel(Resources resources, AnnotationFinder finder, ResourceLoader loader) throws InitializationException {
    ApplicationModel model = createModel(resources, loader);
    for (ExtensionWithId extension : extensions) {
      extension.register(model, finder, loader);
    }
    for (IItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      model.getItemTypeRegistry().registerItemType(itemTypeConfiguration.getItemType());
    }
    for (IItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      itemTypeConfiguration.initModel(model);
    }
    return model;
  }

  private ApplicationModel createModel(Resources resources, ResourceLoader resourceLoader) throws InitializationException {
    try {
      return new ApplicationModel(createRepositoryFolder(), resources, resourceLoader);
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
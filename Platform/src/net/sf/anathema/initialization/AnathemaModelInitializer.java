package net.sf.anathema.initialization;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.util.Collection;

public class AnathemaModelInitializer {

  private final IAnathemaPreferences anathemaPreferences;
  private final Collection<IItemTypeConfiguration> itemTypeConfigurations;
  private Iterable<ExtensionWithId> extensions;

  public AnathemaModelInitializer(
      IAnathemaPreferences anathemaPreferences,
      Collection<IItemTypeConfiguration> itemTypeConfigurations,
      Iterable<ExtensionWithId> extensions) {
    this.anathemaPreferences = anathemaPreferences;
    this.itemTypeConfigurations = itemTypeConfigurations;
    this.extensions = extensions;
  }

  public IAnathemaModel initializeModel(IResources resources) throws InitializationException {
    AnathemaModel model;
    try {
      model = new AnathemaModel(createRepositoryFolder(), resources);
    }
    catch (RepositoryException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e); //$NON-NLS-1$
    }
    for (ExtensionWithId extension : extensions) {
      extension.extension.initialize(resources, model.getRepository());
      model.getExtensionPointRegistry().register(extension.id, extension.extension);
    }
    for (IItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      model.getItemTypeRegistry().registerItemType(itemTypeConfiguration.getItemType());
    }
    for (IItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      itemTypeConfiguration.initModel(model);
    }
    return model;
  }

  private File createRepositoryFolder() throws RepositoryException {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(anathemaPreferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}
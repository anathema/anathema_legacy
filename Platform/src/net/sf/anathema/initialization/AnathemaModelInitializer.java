package net.sf.anathema.initialization;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.module.IItemTypeConfiguration;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaModelInitializer {

  private final IAnathemaPreferences anathemaPreferences;
  private final Collection<IItemTypeConfiguration> itemTypeConfigurations;
  private final Map<String, IAnathemaExtension> extensionById;

  public AnathemaModelInitializer(
      IAnathemaPreferences anathemaPreferences,
      Collection<IItemTypeConfiguration> itemTypeConfigurations,
      Map<String, IAnathemaExtension> extensionById) {
    this.anathemaPreferences = anathemaPreferences;
    this.itemTypeConfigurations = itemTypeConfigurations;
    this.extensionById = extensionById;
  }

  public IAnathemaModel initializeModel(IResources resources) throws InitializationException {
    AnathemaModel model;
    try {
      model = new AnathemaModel(createRepositoryFolder(), resources);
    }
    catch (RepositoryException e) {
      throw new InitializationException("Failed to create repository folder.\nPlease check read/write permissions.", e); //$NON-NLS-1$
    }
    for (Map.Entry<String, IAnathemaExtension> entry : extensionById.entrySet()) {
      IAnathemaExtension extension = entry.getValue();
      extension.initialize(resources, model.getRepository());
      model.getExtensionPointRegistry().register(entry.getKey(), extension);
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
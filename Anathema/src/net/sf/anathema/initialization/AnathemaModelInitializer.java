package net.sf.anathema.initialization;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.modules.ModelExtensionPointFiller;
import net.sf.anathema.initialization.modules.ModelExtensionPointInitializer;
import net.sf.anathema.initialization.modules.ModelInitializer;
import net.sf.anathema.initialization.repository.IOFileSystemAbstraction;
import net.sf.anathema.initialization.repository.RepositoryFolderCreator;
import net.sf.anathema.initialization.repository.RepositoryLocationResolver;
import net.sf.anathema.lib.resources.IResources;

public class AnathemaModelInitializer {

  private final IAnathemaPreferences anathemaPreferences;
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations;
  private final Map<String, IAnathemaExtension> extensionById;

  public AnathemaModelInitializer(
      IAnathemaPreferences anathemaPreferences,
      Collection<AbstractItemTypeConfiguration> itemTypeConfigurations,
      Map<String, IAnathemaExtension> extensionById) {
    this.anathemaPreferences = anathemaPreferences;
    this.itemTypeConfigurations = itemTypeConfigurations;
    this.extensionById = extensionById;
  }

  public IAnathemaModel initializeModel(IModuleCollection moduleCollection, IResources resources)
      throws RepositoryException {
    AnathemaModel model = new AnathemaModel(createRepositoryFolder());
    new ModelExtensionPointInitializer(moduleCollection, model.getExtensionPointRegistry(), model, resources).initialize();
    for (Map.Entry<String, IAnathemaExtension> entry : extensionById.entrySet()) {
      IAnathemaExtension extension = entry.getValue();
      extension.initialize(resources);
      model.getExtensionPointRegistry().register(entry.getKey(), extension);
    }

    new ModelExtensionPointFiller(moduleCollection, model.getExtensionPointRegistry(), model).initialize();
    for (AbstractItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      model.getItemTypeRegistry().registerItemType(itemTypeConfiguration.getItemType());
    }
    new ModelInitializer(moduleCollection, model, resources).initialize();
    for (AbstractItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
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
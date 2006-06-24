package net.sf.anathema.initialization;

import java.io.File;
import java.util.Collection;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.model.AnathemaModel;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.modules.IModuleCollection;
import net.sf.anathema.initialization.modules.ItemTypeInitializer;
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

  public AnathemaModelInitializer(
      IAnathemaPreferences anathemaPreferences,
      Collection<AbstractItemTypeConfiguration> itemTypeConfigurations) {
    this.anathemaPreferences = anathemaPreferences;
    this.itemTypeConfigurations = itemTypeConfigurations;
  }

  public IAnathemaModel initializeModel(IModuleCollection moduleCollection, IResources resources)
      throws RepositoryException {
    AnathemaModel model = new AnathemaModel(createRepositoryFolder());
    new ModelExtensionPointInitializer(moduleCollection, model.getExtensionPointRegistry(), model, resources).initialize();
    new ModelExtensionPointFiller(moduleCollection, model.getExtensionPointRegistry(), model).initialize();
    for (AbstractItemTypeConfiguration itemTypeConfiguration : itemTypeConfigurations) {
      model.getItemTypeRegistry().registerItemType(itemTypeConfiguration.getItemType());
    }
    new ItemTypeInitializer(moduleCollection, model.getItemTypeRegistry()).initialize();
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
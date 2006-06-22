package net.sf.anathema.initialization;

import java.io.File;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.framework.model.AnathemaModel;
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

  public AnathemaModelInitializer(IAnathemaPreferences anathemaPreferences) {
    this.anathemaPreferences = anathemaPreferences;
  }

  public IAnathemaModel initializeModel(IModuleCollection moduleCollection, IResources resources)
      throws RepositoryException {
    AnathemaModel model = new AnathemaModel(createRepositoryFolder());
    new ModelExtensionPointInitializer(moduleCollection, model.getExtensionPointRegistry(), model, resources).initialize();
    new ModelExtensionPointFiller(moduleCollection, model.getExtensionPointRegistry(), model).initialize();
    new ItemTypeInitializer(moduleCollection, model.getItemTypeRegistry()).initialize();
    new ModelInitializer(moduleCollection, model, resources).initialize();
    return model;
  }

  private File createRepositoryFolder() throws RepositoryException {
    IOFileSystemAbstraction fileSystem = new IOFileSystemAbstraction();
    RepositoryLocationResolver repositoryLocationResolver = new RepositoryLocationResolver(anathemaPreferences);
    return new RepositoryFolderCreator(fileSystem, repositoryLocationResolver).createRepositoryFolder();
  }
}
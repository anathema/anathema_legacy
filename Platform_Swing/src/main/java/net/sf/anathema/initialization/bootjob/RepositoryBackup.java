package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.tree.FileExporter;
import net.sf.anathema.framework.repository.tree.RepositoryZipPathCreator;
import net.sf.anathema.initialization.ItemTypeCollection;
import net.sf.anathema.lib.resources.Resources;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositoryBackup {
  private Resources resources;
  private IApplicationModel model;

  public RepositoryBackup(Resources resources, IApplicationModel model) {
    this.resources = resources;
    this.model = model;
  }

  public void backupRepository() {
    try {
      Repository repository = model.getRepository();
      ItemTypeCollection itemTypeCollection = new ItemTypeCollection(model.getObjectFactory());
      CleanupExportModel exportModel = new CleanupExportModel(itemTypeCollection, repository);
      if (exportModel.getPrintNameFilesInSelection().length == 0) {
        return;
      }
      RepositoryZipPathCreator creator = new RepositoryZipPathCreator(repository.getRepositoryPath());
      Path saveFile = getSaveFile();
      new FileExporter(creator, exportModel, resources).exportToZip(saveFile);
    } catch (IOException e) {
      throw new RuntimeException("Could not back up repository before launch. Please create a copy and delete all 1E characters manually.", e);
    }
  }

  private Path getSaveFile() {
    Repository repository = model.getRepository();
    String version = new Version(resources).asString();
    return Paths.get(repository.getRepositoryPath()).resolve("BackupForFirstLaunchOf" + version + ".zip");
  }
}
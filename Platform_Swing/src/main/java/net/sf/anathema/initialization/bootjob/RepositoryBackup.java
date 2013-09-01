package net.sf.anathema.initialization.bootjob;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.Version;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.tree.FileExporter;
import net.sf.anathema.framework.repository.tree.RepositoryZipPathCreator;
import net.sf.anathema.initialization.ItemTypeCollection;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RepositoryBackup {
  private final Environment environment;
  private final IApplicationModel model;

  public RepositoryBackup(Environment environment, IApplicationModel model) {
    this.environment = environment;
    this.model = model;
  }

  public void backupRepository() {
    try {
      Repository repository = model.getRepository();
      ItemTypeCollection itemTypeCollection = new ItemTypeCollection(environment);
      CleanupExportModel exportModel = new CleanupExportModel(itemTypeCollection, repository);
      if (exportModel.getPrintNameFilesInSelection().length == 0) {
        return;
      }
      RepositoryZipPathCreator creator = new RepositoryZipPathCreator(repository.getRepositoryPath());
      Path saveFile = getSaveFile();
      new FileExporter(creator, exportModel, environment).exportToZip(saveFile);
    } catch (IOException e) {
      throw new RuntimeException("Could not back up repository before launch. Please create a copy and delete all 1E characters manually.", e);
    }
  }

  private Path getSaveFile() {
    Repository repository = model.getRepository();
    String version = new Version(environment).asString();
    return Paths.get(repository.getRepositoryPath()).resolve("BackupForFirstLaunchOf" + version + ".zip");
  }
}
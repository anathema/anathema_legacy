package net.sf.anathema.character.generic.impl.bootjob;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.tree.FileExporter;
import net.sf.anathema.framework.repository.tree.RepositoryZipPathCreator;
import net.sf.anathema.lib.resources.IResources;

import java.io.File;
import java.io.IOException;

public class RepositoryBackup {
  public void backupRepository(IResources resources, IAnathemaModel model) {
    try {
      IRepository repository = model.getRepository();
      IItemType[] itemTypes = model.getItemTypeRegistry().getAllItemTypes();
      CleanupExportModel exportModel = new CleanupExportModel(itemTypes, repository);
      if (exportModel.getPrintNameFilesInSelection().length == 0) {
        return;
      }
      RepositoryZipPathCreator creator = new RepositoryZipPathCreator(repository.getRepositoryPath());
      String version = resources.getString("Anathema.Version.Numeric");
      File saveFile = new File(repository.getRepositoryPath(), "BackupForFirstLaunchOf" + version + ".zip");
      if (saveFile.exists()) {
        return;
      }
      new FileExporter(creator, exportModel, resources).exportToZip(saveFile);
    } catch (IOException e) {
      throw new RuntimeException(
              "Could not back up repository before launch. Please create a copy and delete all 1E characters manually.",
              e);
    }
  }
}
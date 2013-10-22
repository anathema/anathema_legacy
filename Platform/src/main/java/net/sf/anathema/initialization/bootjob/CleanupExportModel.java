package net.sf.anathema.initialization.bootjob;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.access.RepositoryFileAccess;
import net.sf.anathema.framework.repository.tree.ExportModel;
import net.sf.anathema.framework.repository.tree.RepositoryFileAccessFactory;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.ItemTypeCollection;

import java.util.List;

public class CleanupExportModel implements ExportModel {
  private final ItemTypeCollection allItemTypes;
  private final Repository repository;
  private final RepositoryFileAccessFactory accessFactory;

  public CleanupExportModel(ItemTypeCollection allItemTypes, Repository repository) {
    this.allItemTypes = allItemTypes;
    this.repository = repository;
    this.accessFactory = new RepositoryFileAccessFactory(repository);
  }

  @Override
  public PrintNameFile[] getPrintNameFilesInSelection() {
    List<PrintNameFile> files = Lists.newArrayList();
    for (IItemType itemType : allItemTypes) {
      if (itemType.supportsRepository()) {
        files.addAll(repository.getPrintNameFileAccess().collectAllPrintNameFiles(itemType));
      }
    }
    return files.toArray(new PrintNameFile[files.size()]);
  }

  @Override
  public RepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    return accessFactory.getFileAccess(printNameFile);
  }
}
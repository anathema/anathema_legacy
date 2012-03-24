package net.sf.anathema.character.generic.impl.bootjob;

import com.google.common.collect.Lists;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.repository.tree.ExportModel;
import net.sf.anathema.framework.repository.tree.RepositoryFileAccessFactory;
import net.sf.anathema.framework.view.PrintNameFile;

import java.util.Collections;
import java.util.List;

public class CleanupExportModel implements ExportModel {
  private IItemType[] allItemTypes;
  private IRepository repository;
  private RepositoryFileAccessFactory accessFactory;

  public CleanupExportModel(IItemType[] allItemTypes, IRepository repository) {
    this.allItemTypes = allItemTypes;
    this.repository = repository;
    this.accessFactory = new RepositoryFileAccessFactory(repository);
  }

  @Override
  public PrintNameFile[] getPrintNameFilesInSelection() {
    List<PrintNameFile> files = Lists.newArrayList();
    for (IItemType itemType : allItemTypes) {
      if (itemType.supportsRepository()) {
        Collections.addAll(files, repository.getPrintNameFileAccess().collectAllPrintNameFiles(itemType));
      }
    }
    return files.toArray(new PrintNameFile[files.size()]);
  }

  @Override
  public IRepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    return accessFactory.getFileAccess(printNameFile);
  }
}
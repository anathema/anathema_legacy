package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

public interface ExportModel {
  PrintNameFile[] getPrintNameFilesInSelection();

  IRepositoryFileAccess getFileAccess(PrintNameFile printNameFile);
}

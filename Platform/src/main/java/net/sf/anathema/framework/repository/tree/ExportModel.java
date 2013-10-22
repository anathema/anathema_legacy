package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.repository.access.RepositoryFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;

public interface ExportModel {
  PrintNameFile[] getPrintNameFilesInSelection();

  RepositoryFileAccess getFileAccess(PrintNameFile printNameFile);
}

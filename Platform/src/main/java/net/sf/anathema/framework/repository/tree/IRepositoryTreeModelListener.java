package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.view.PrintNameFile;

public interface IRepositoryTreeModelListener {

  void printNameFileAdded(PrintNameFile file);

  void printNameFileRemoved(PrintNameFile file);
}
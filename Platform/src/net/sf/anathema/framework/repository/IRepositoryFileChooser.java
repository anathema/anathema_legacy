package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public interface IRepositoryFileChooser {

  public File getRepositoryFile(IItemType type) throws RepositoryException;
}
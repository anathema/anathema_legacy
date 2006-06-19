package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;

public interface IRepository {

  public IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException;

  public IRepositoryReadAccess openReadAccess(IItemType type, IRepositoryFileChooser fileChooser)
      throws RepositoryException;

  public IPrintNameFileAccess getPrintNameFileAccess();

  public boolean containsClosed(IItemType type);

  public File getRepositoryFolder();
}
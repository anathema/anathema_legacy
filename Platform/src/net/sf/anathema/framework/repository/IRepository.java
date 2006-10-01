package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.initialization.repository.IDataFileProvider;

public interface IRepository extends IDataFileProvider {

  public IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException;

  public IRepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider);

  public IPrintNameFileAccess getPrintNameFileAccess();

  public File getRepositoryFolder();

  public boolean containsClosed(IItemType... type);
}
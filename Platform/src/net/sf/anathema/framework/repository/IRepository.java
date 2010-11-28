package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IRepository extends IDataFileProvider {

  public IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException;

  public IRepositoryWriteAccess createWriteAccess(IItemType type, String id) throws RepositoryException;

  public IRepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider);

  public IPrintNameFileAccess getPrintNameFileAccess();

  public String getRepositoryPath();

  public boolean containsClosed(IItemType... type);

  public void deleteAssociatedItem(PrintNameFile userObject) throws RepositoryException;

  public String createUniqueRepositoryId(IBasicRepositoryIdData repositoryLocation);

  public IRepositoryFileResolver getRepositoryFileResolver();

  public void addRefreshListener(IChangeListener listener);

  public void refresh();
}
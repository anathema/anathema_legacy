package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.IPrintNameFileAccess;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.control.IChangeListener;

public interface IRepository extends IDataFileProvider {

  IRepositoryWriteAccess createWriteAccess(IItem item) throws RepositoryException;

  IRepositoryWriteAccess createWriteAccess(IItemType type, String id) throws RepositoryException;

  IRepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider);
  
  IRepositoryReadAccess openReadAccess(IItemType type, String id);

  boolean knowsItem(IItemType type, String id);

  IPrintNameFileAccess getPrintNameFileAccess();

  String getRepositoryPath();

  boolean containsClosed(IItemType... type);

  void deleteAssociatedItem(PrintNameFile userObject) throws RepositoryException;

  String createUniqueRepositoryId(IBasicRepositoryIdData repositoryLocation);

  IRepositoryFileResolver getRepositoryFileResolver();

  void addRefreshListener(IChangeListener listener);

  void refresh();
}

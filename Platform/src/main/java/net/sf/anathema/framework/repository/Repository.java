package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.action.IFileProvider;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.framework.repository.access.printname.PrintNameFileAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceAccess;
import net.sf.anathema.framework.repository.access.printname.ReferenceBuilder;
import net.sf.anathema.framework.view.PrintNameFile;
import net.sf.anathema.initialization.repository.DataFileProvider;

public interface Repository extends DataFileProvider {

  IRepositoryWriteAccess createWriteAccess(IItemType type, String id) throws RepositoryException;

  IRepositoryReadAccess openReadAccess(IItemType type, IFileProvider provider);
  
  IRepositoryReadAccess openReadAccess(IItemType type, String id);

  boolean knowsItem(IItemType type, String id);

  PrintNameFileAccess getPrintNameFileAccess();

  <R> ReferenceAccess<R> createReferenceAccess(IItemType type, ReferenceBuilder<R> builder);

  String getRepositoryPath();

  void deleteAssociatedItem(PrintNameFile userObject) throws RepositoryException;

  String createUniqueRepositoryId(IBasicRepositoryIdData repositoryLocation);

  IRepositoryFileResolver getRepositoryFileResolver();
}
package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public interface IRepositoryFileResolver {

  public File getMainFile(IItemType type, String id);

  public File getItemTypeFolder(IItemType type);

  public File getMainFile(File folder, IItemType itemType);

}

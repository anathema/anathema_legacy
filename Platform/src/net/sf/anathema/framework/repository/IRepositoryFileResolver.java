package net.sf.anathema.framework.repository;

import java.io.File;

import net.sf.anathema.framework.item.IItemType;

public interface IRepositoryFileResolver {

  File getMainFile(IItemType type, String id);

  File getItemTypeFolder(IItemType type);

  File getMainFile(File folder, IItemType itemType);
}
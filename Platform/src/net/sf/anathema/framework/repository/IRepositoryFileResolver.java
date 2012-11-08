package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IItemType;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public interface IRepositoryFileResolver {

  File getMainFile(IItemType type, String id);

  File getItemTypeFolder(IItemType type);

  File getMainFile(File folder, IItemType itemType);

  Collection<Path> listAllFiles(IItemType equipmentType);
}
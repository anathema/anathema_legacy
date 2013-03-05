package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.IRepositoryConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public interface IRepositoryFileResolver {

  File getMainFile(IRepositoryConfiguration configuration, String id);

  File getMainFile(File folder, IRepositoryConfiguration configuration);

  File getFolder(IRepositoryConfiguration configuration);

  Collection<Path> listAllFiles(IRepositoryConfiguration configuration);
}
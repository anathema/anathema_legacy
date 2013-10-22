package net.sf.anathema.framework.repository;

import net.sf.anathema.framework.item.RepositoryConfiguration;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public interface IRepositoryFileResolver {

  File getMainFile(RepositoryConfiguration configuration, String id);

  File getMainFile(File folder, RepositoryConfiguration configuration);

  File getFolder(RepositoryConfiguration configuration);

  Collection<Path> listAllFiles(RepositoryConfiguration configuration);
}
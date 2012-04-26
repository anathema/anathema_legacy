package net.sf.anathema.framework.module.preferences;

import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.initialization.repository.IStringResolver;

import java.io.File;
import java.io.IOException;

public class CanonicalPathResolver implements IStringResolver {

  private final File repositoryDirectory;

  public CanonicalPathResolver(File repositoryDirectory) {
    this.repositoryDirectory = repositoryDirectory;
  }

  @Override
  public String resolve() {
    try {
      return repositoryDirectory.getCanonicalPath();
    } catch (IOException e) {
      throw new RepositoryException("Could not resolve path " + repositoryDirectory.getAbsolutePath());
    }
  }
}

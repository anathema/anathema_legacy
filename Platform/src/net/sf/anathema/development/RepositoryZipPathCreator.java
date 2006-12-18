package net.sf.anathema.development;

import java.io.File;

public class RepositoryZipPathCreator {

  private final String repositoryPath;

  public RepositoryZipPathCreator(String repositoryPath) {
    this.repositoryPath = repositoryPath;
  }

  public String createZipPath(File file) {
    String path = file.getPath();
    return createZipPath(path);
  }

  public String createZipPath(String path) {
    return path.replace(repositoryPath, "").replace(File.separatorChar, '/'); //$NON-NLS-1$
  }
}
package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.presenter.action.ConfigurableFileProvider;
import net.sf.anathema.framework.repository.Repository;
import net.sf.anathema.framework.repository.access.RepositoryFileAccess;
import net.sf.anathema.framework.repository.access.RepositoryReadAccess;
import net.sf.anathema.framework.view.PrintNameFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RepositoryFileAccessFactory {
  private final Repository repository;

  public RepositoryFileAccessFactory(Repository repository) {
    this.repository = repository;
  }

  public RepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    ConfigurableFileProvider provider = new ConfigurableFileProvider();
    provider.setFile(printNameFile.getFile());
    final RepositoryReadAccess access = repository.openReadAccess(printNameFile.getItemType(), provider);
    return new RepositoryFileAccess() {
      @Override
      public File[] getFiles() {
        return access.getFiles();
      }

      @Override
      public InputStream openInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
      }
    };
  }
}
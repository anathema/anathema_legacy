package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.presenter.action.ConfigurableFileProvider;
import net.sf.anathema.framework.repository.IRepository;
import net.sf.anathema.framework.repository.access.IRepositoryFileAccess;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.view.PrintNameFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RepositoryFileAccessFactory {
  private final IRepository repository;

  public RepositoryFileAccessFactory(IRepository repository) {
    this.repository = repository;
  }

  public IRepositoryFileAccess getFileAccess(PrintNameFile printNameFile) {
    ConfigurableFileProvider provider = new ConfigurableFileProvider();
    provider.setFile(printNameFile.getFile());
    final IRepositoryReadAccess access = repository.openReadAccess(printNameFile.getItemType(), provider);
    return new IRepositoryFileAccess() {
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
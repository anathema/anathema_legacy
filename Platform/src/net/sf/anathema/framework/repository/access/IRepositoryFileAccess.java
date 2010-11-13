package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface IRepositoryFileAccess extends IRepositoryFileProvider {

  public InputStream openInputStream(File file) throws FileNotFoundException;
}
package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface RepositoryFileAccess extends RepositoryFileProvider {

  InputStream openInputStream(File file) throws FileNotFoundException;
}
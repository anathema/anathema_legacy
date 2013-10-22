package net.sf.anathema.framework.repository.access;

import java.io.File;

public interface RepositoryFileProvider {

  File[] getFiles();
}
package net.sf.anathema.framework.repository.access;

import java.io.File;
import java.io.InputStream;

import net.sf.anathema.framework.repository.RepositoryException;

public interface IRepositoryReadAccess {

  public InputStream openMainInputStream() throws RepositoryException;

  public InputStream openSubInputStream(String streamID) throws RepositoryException;

  public File[] getAllFiles();
}
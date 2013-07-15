package net.sf.anathema.framework.repository.access;

import net.sf.anathema.framework.repository.RepositoryException;

import java.io.InputStream;

public interface IRepositoryReadAccess extends IRepositoryFileProvider {

  InputStream openMainInputStream() throws RepositoryException;

  InputStream openSubInputStream(String streamID) throws RepositoryException;
}
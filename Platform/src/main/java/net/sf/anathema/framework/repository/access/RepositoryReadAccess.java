package net.sf.anathema.framework.repository.access;

import net.sf.anathema.framework.repository.RepositoryException;

import java.io.InputStream;

public interface RepositoryReadAccess extends RepositoryFileProvider {

  InputStream openMainInputStream() throws RepositoryException;

  InputStream openSubInputStream(String streamID) throws RepositoryException;
}
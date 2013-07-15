package net.sf.anathema.framework.repository.access;

import net.sf.anathema.framework.repository.RepositoryException;

import java.io.OutputStream;

public interface IRepositoryWriteAccess {

  OutputStream createMainOutputStream() throws RepositoryException;

  OutputStream createSubOutputStream(String streamID) throws RepositoryException;
}

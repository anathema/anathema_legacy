package net.sf.anathema.framework.repository.access;

import java.io.OutputStream;

import net.sf.anathema.framework.repository.RepositoryException;

public interface IRepositoryWriteAccess {

  OutputStream createMainOutputStream() throws RepositoryException;

  OutputStream createSubOutputStream(String streamID) throws RepositoryException;
}

package net.sf.anathema.framework.persistence;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryReadAccess;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractSingleFileItemPersister implements IRepositoryItemPersister {

  @Override
  public void save(IRepositoryWriteAccess writeAccess, IItem item) throws IOException, RepositoryException {
    OutputStream stream = null;
    try {
      stream = writeAccess.createMainOutputStream();
      save(stream, item);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  protected abstract void save(OutputStream stream, IItem item) throws IOException;

  @Override
  public final IItem load(IRepositoryReadAccess readAccess) throws PersistenceException, RepositoryException {
    InputStream stream = null;
    try {
      if (readAccess == null) {
        return null;
      }
      stream = readAccess.openMainInputStream();
      SAXReader saxReader = new SAXReader();
      Document document = saxReader.read(stream);
      return load(document);
    }
    catch (DocumentException e) {
      throw new PersistenceException(e);
    }
    finally {
      IOUtils.closeQuietly(stream);
    }
  }

  protected abstract IItem load(Document document) throws PersistenceException;
}
package net.sf.anathema.framework.repository.tree;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.IRepositoryWriteAccess;

import org.apache.commons.io.IOUtils;

public class RepositoryImportHandler {

  private final IRepositoryWriteAccess access;
  private final String oldId;
  private final String newId;

  public RepositoryImportHandler(IRepositoryTreeModel model, IItemType type, String oldId) throws RepositoryException {
    this.newId = model.createUniqueId(type, oldId);
    this.access = model.getWriteAccess(type, newId);
    this.oldId = oldId;
  }

  public void importStream(String mainFilePath, InputStream inputStream, String entryName)
      throws RepositoryException,
      IOException {
    if (entryName.equals(mainFilePath)) {
      writeMainFile(inputStream);
    }
    else {
      writeSubFile(inputStream, entryName);
    }
  }

  private void writeMainFile(InputStream inputStream) throws RepositoryException, IOException {
    String string = IOUtils.toString(inputStream);
    inputStream.close();
    string = string.replaceFirst("repositoryId=\"" + oldId + "\"", "repositoryId=\"" + newId + "\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    OutputStream outputStream = access.createMainOutputStream();
    PrintWriter writer = new PrintWriter(outputStream);
    writer.write(string);
    writer.close();
  }

  private void writeSubFile(InputStream inputStream, String entryName) throws RepositoryException, IOException {
    String unextendedFileName = entryName.substring(entryName.lastIndexOf("/") + 1, entryName.lastIndexOf(".")); //$NON-NLS-1$ //$NON-NLS-2$
    OutputStream outputStream = access.createSubOutputStream(unextendedFileName);
    importStreamToRepository(inputStream, outputStream);
  }

  private void importStreamToRepository(InputStream importStream, OutputStream repositoryStream) throws IOException {
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(repositoryStream, 512);
    StreamUtilities.writeInputStreamToOutputStream(importStream, bufferedOutputStream);
    bufferedOutputStream.close();
  }

  public String getNewId() {
    return newId;
  }
}
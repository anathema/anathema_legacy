package net.sf.anathema.framework.repository.tree;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.RepositoryException;
import net.sf.anathema.framework.repository.access.RepositoryWriteAccess;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RepositoryImportHandler {

  private final RepositoryWriteAccess access;
  private final String oldId;
  private final String newId;

  public RepositoryImportHandler(IRepositoryTreeModel model, IItemType type, String oldId) throws RepositoryException {
    this.newId = model.createUniqueId(type, oldId);
    this.access = model.getWriteAccess(type, newId);
    this.oldId = oldId;
  }

  public void importStream(String mainFilePath, InputStream inputStream,
                           String entryName) throws RepositoryException, IOException {
    if (entryName.equals(mainFilePath)) {
      writeMainFileWithLegalId(inputStream);
    } else {
      writeSubFile(inputStream, entryName);
    }
  }

  private void writeMainFileWithLegalId(InputStream inputStream) throws RepositoryException, IOException {
    InputStream modifiedInput = new ImportIdReplacer(oldId, newId).createStreamWithLegalId(inputStream);
    writeMainFile(modifiedInput);
  }

  private void writeMainFile(InputStream modifiedInput) throws IOException {
    OutputStream outputStream = access.createMainOutputStream();
    importStreamToRepository(modifiedInput, outputStream);
    outputStream.close();
    modifiedInput.close();
  }

  private void writeSubFile(InputStream inputStream, String entryName) throws RepositoryException, IOException {
    String unextendedFileName = entryName.substring(entryName.lastIndexOf(File.separator) + 1,
            entryName.lastIndexOf("."));
    OutputStream outputStream = access.createSubOutputStream(unextendedFileName);
    importStreamToRepository(inputStream, outputStream);
    outputStream.close();
  }

  private void importStreamToRepository(InputStream importStream, OutputStream repositoryStream) throws IOException {
    IOUtils.copy(importStream, repositoryStream);
  }

  public String getNewId() {
    return newId;
  }
}
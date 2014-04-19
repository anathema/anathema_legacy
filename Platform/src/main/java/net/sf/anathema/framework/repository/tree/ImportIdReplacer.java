package net.sf.anathema.framework.repository.tree;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImportIdReplacer {
  private final String oldId;
  private final String newId;

  public ImportIdReplacer(String oldId, String newId) {
    this.oldId = oldId;
    this.newId = newId;
  }

  public InputStream createStreamWithLegalId(InputStream inputStream) throws IOException {
    String string = IOUtils.toString(inputStream);
    string = string.replaceFirst("\"repositoryId\": \"" + oldId + "\"",
            "\"repositoryId\": \"" + newId + "\"");
    return new ByteArrayInputStream(string.getBytes());
  }
}
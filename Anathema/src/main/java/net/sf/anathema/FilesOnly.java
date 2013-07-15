package net.sf.anathema;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesOnly implements DirectoryStream.Filter<Path> {
  @Override
  public boolean accept(Path entry) throws IOException {
    return (Files.isRegularFile(entry));
  }
}
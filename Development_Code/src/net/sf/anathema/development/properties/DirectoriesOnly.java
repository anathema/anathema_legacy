package net.sf.anathema.development.properties;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoriesOnly implements DirectoryStream.Filter<Path> {
  @Override
  public boolean accept(Path entry) throws IOException {
    return Files.isDirectory(entry);
  }
}
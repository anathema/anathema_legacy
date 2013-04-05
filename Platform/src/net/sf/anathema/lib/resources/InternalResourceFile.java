package net.sf.anathema.lib.resources;

import java.net.URL;

public class InternalResourceFile implements ResourceFile {
  private final String fileName;
  private final ClassLoader loader;

  public InternalResourceFile(String fileName, ClassLoader loader) {
    this.fileName = fileName;
    this.loader = loader;
  }

  @Override
  public URL getURL() {
    return loader.getResource(fileName);
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
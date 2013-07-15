package net.sf.anathema.platform.fx;

import net.sf.anathema.lib.file.RelativePath;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {

  public InputStream loadResource(RelativePath pathRelativeToSourceFolder) {
    return getClassLoader().getResourceAsStream(pathRelativeToSourceFolder.relativePath);
  }

  public URL loadResourceAsUrl(String pathRelativeToSourceFolder) {
    return getClassLoader().getResource(pathRelativeToSourceFolder);
  }

  private ClassLoader getClassLoader() {
    return getClass().getClassLoader();
  }
}
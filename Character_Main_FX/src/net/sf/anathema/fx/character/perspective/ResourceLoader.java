package net.sf.anathema.fx.character.perspective;

import java.io.InputStream;
import java.net.URL;

public class ResourceLoader {

  public InputStream loadResource(String pathRelativeToSourceFolder) {
    return getClassLoader().getResourceAsStream(pathRelativeToSourceFolder);
  }

  public URL loadResourceAsUrl(String pathRelativeToSourceFolder) {
    return getClassLoader().getResource(pathRelativeToSourceFolder);
  }

  private ClassLoader getClassLoader() {
    return getClass().getClassLoader();
  }
}
package net.sf.anathema.platform.tool;

import javafx.scene.image.Image;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.ResourceLoader;

import java.io.InputStream;

public class LoadImage {
  private final RelativePath relativePath;

  public LoadImage(RelativePath relativePath) {
    this.relativePath = relativePath;
  }

  public Image run() {
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(relativePath);
    return new Image(imageStream);
  }
}

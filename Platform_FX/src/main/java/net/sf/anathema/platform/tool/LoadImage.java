package net.sf.anathema.platform.tool;

import javafx.scene.image.Image;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.ResourceLoader;

import java.io.InputStream;

import static net.sf.anathema.lib.gui.AgnosticUIConfiguration.NO_ICON;

public class LoadImage {
  private final RelativePath relativePath;

  public LoadImage(RelativePath relativePath) {
    this.relativePath = relativePath;
  }

  public ImageContainer run() {
    if (relativePath == NO_ICON) {
      return new NullImageContainer();
    }
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(relativePath);
    return new DefaultImageContainer(new Image(imageStream));
  }
}
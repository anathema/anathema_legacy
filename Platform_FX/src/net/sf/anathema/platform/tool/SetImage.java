package net.sf.anathema.platform.tool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.fx.ResourceLoader;

import java.io.InputStream;

public class SetImage implements Runnable {
  private final NodeHolder<ImageView> imageView;
  private final RelativePath relativePath;

  public SetImage(NodeHolder<ImageView> imageView, RelativePath relativePath) {
    this.imageView = imageView;
    this.relativePath = relativePath;
  }

  @Override
  public void run() {
    imageView.getNode().setImage(createImage(relativePath));
  }

  private Image createImage(RelativePath pathToImage) {
    ResourceLoader resourceLoader = new ResourceLoader();
    InputStream imageStream = resourceLoader.loadResource(pathToImage);
    return new Image(imageStream, 20, 20, true, true);
  }
}
package net.sf.anathema.platform.tool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SetImage implements ImageClosure {
  private ImageView imageView;

  public SetImage(ImageView imageView) {
    this.imageView = imageView;
  }

  @Override
  public void run(Image image) {
    imageView.setImage(image);
  }
}
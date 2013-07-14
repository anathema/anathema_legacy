package net.sf.anathema.platform.tool;

import javafx.scene.image.ImageView;
import net.sf.anathema.framework.ui.Area;

public class NullImageContainer implements ImageContainer {

  private static final Area DEFAULT_ICON_SIZE = new Area(16, 16);

  @Override
  public Area getArea() {
    return DEFAULT_ICON_SIZE;
  }

  @Override
  public void displayIn(ImageView imageView) {
    imageView.setImage(null);
  }
}

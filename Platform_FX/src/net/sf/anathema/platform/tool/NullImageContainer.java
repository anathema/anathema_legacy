package net.sf.anathema.platform.tool;

import javafx.scene.image.ImageView;
import net.sf.anathema.framework.ui.Area;

public class NullImageContainer implements ImageContainer {
  @Override
  public Area getArea() {
    return new Area(17, 17);
  }

  @Override
  public void displayIn(ImageView imageView) {
    imageView.setImage(null);
  }
}

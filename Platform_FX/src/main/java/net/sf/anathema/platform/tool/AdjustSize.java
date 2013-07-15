package net.sf.anathema.platform.tool;

import javafx.scene.control.ButtonBase;
import net.sf.anathema.framework.ui.Area;

public class AdjustSize implements ImageClosure {
  private ButtonBase button;

  public AdjustSize(ButtonBase button) {
    this.button = button;
  }

  @Override
  public void run(ImageContainer image) {
    Area imageArea = image.getArea();
    double targetWidth = imageArea.width + 1;
    double targetHeight = imageArea.height + 1;
    button.setMinSize(targetWidth, targetHeight);
    button.setPrefSize(targetWidth, targetHeight);
    button.setMaxSize(targetWidth, targetHeight);
  }
}

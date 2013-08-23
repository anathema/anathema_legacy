package net.sf.anathema.platform.tool;

import javafx.scene.control.ButtonBase;
import net.sf.anathema.framework.ui.Area;

public class AdjustSize implements ImageClosure {
  private final ButtonBase button;
  private int extraWidth = 0;

  public AdjustSize(ButtonBase button) {
    this.button = button;
  }

  public void addExtraWidth(int extraWidth) {
    this.extraWidth = extraWidth;
  }

  @Override
  public void run(ImageContainer image) {
    Area imageArea = image.getArea();
    double targetWidth = imageArea.width + 1 + extraWidth;
    double targetHeight = imageArea.height + 1;
    button.setMinSize(targetWidth, targetHeight);
    button.setPrefSize(targetWidth, targetHeight);
    button.setMaxSize(targetWidth, targetHeight);
  }
}

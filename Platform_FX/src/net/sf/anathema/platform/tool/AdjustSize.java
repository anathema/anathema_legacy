package net.sf.anathema.platform.tool;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class AdjustSize implements ImageClosure {
  private Button button;

  public AdjustSize(Button button) {
    this.button = button;
  }

  @Override
  public void run(Image image) {
    double targetWidth = image.getWidth() + 1;
    double targetHeight = image.getHeight() + 1;
    button.setMinSize(targetWidth, targetHeight);
    button.setPrefSize(targetWidth, targetHeight);
    button.setMaxSize(targetWidth, targetHeight);
  }
}

package net.sf.anathema.platform.tree.fx;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import net.sf.anathema.framework.ui.RGBColor;

public class FxColorUtils {
  public static Paint toFxColor(RGBColor color) {
    return new Color(color.red, color.green, color.blue, color.alpha / 255.0);
  }
}

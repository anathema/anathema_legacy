package net.sf.anathema.platform.tree.fx;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import net.sf.anathema.framework.ui.RGBColor;

public class FxColorUtils {

  public static final double Normalizer = 255.0;

  public static Paint toFxColor(RGBColor color) {
    double fxRed = color.red / Normalizer;
    double fxGreen = color.green / Normalizer;
    double fxBlue = color.blue / Normalizer;
    double fxAlpha = color.alpha / Normalizer;
    return new Color(fxRed, fxGreen, fxBlue, fxAlpha);
  }
}
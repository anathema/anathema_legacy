package net.sf.anathema.lib.gui.swing;

import net.sf.anathema.framework.ui.RGBColor;

import java.awt.Color;

public class ColorUtilities {
  public static Color getTransparency() {
    return getTransparentColor(Color.WHITE, 0);
  }

  public static Color getTransparentColor(Color original, int alpha) {
    return new Color(original.getRed(), original.getGreen(), original.getBlue(), alpha);
  }

  public static Color toAwtColor(RGBColor color) {
    return new Color(color.red, color.green, color.blue, color.alpha);
  }
}
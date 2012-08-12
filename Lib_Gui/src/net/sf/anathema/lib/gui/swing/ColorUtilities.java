package net.sf.anathema.lib.gui.swing;

import java.awt.Color;

public class ColorUtilities {
  public static Color getTransparentColor(Color original, int alpha) {
    return new Color(original.getRed(), original.getGreen(), original.getBlue(),alpha);
  }
}

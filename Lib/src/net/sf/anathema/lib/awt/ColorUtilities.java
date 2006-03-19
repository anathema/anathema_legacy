package net.sf.anathema.lib.awt;

import java.awt.Color;

public class ColorUtilities {

  public static String convertColorToHexString(Color color) {
    return "#" //$NON-NLS-1$
        + getColorHexString(color.getRed())
        + getColorHexString(color.getGreen())
        + getColorHexString(color.getBlue());
  }

  private static String getColorHexString(int colorValue) {
    return colorValue == 0 ? "00" : Integer.toHexString(colorValue); //$NON-NLS-1$
  }
}
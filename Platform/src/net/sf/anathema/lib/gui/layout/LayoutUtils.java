package net.sf.anathema.lib.gui.layout;

import net.miginfocom.layout.LC;

public class LayoutUtils {

  public static LC withoutInsets() {
    return new LC().insets("0");
  }

  public static LC fillWithoutInsets() {
    return withoutInsets().fill();
  }
}
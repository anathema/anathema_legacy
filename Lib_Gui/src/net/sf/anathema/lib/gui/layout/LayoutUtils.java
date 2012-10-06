package net.sf.anathema.lib.gui.layout;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;

import javax.swing.JComponent;

public class LayoutUtils {
  public static final int DEFAULT_COMPONENT_SPACING = 11;

  public static CC constraintsForImageButton(JComponent button) {
    String width = String.valueOf(button.getPreferredSize().width);
    String height = String.valueOf(button.getPreferredSize().height);
    return new CC().maxWidth(width).maxHeight(height);
  }

  public static int getComponentSpacing() {
    return DEFAULT_COMPONENT_SPACING;
  }

  public static LC withoutInsets() {
    return new LC().insets("0");
  }

  public static LC fillWithoutInsets() {
    return withoutInsets().fill();
  }
}
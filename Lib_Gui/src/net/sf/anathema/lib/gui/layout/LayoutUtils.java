package net.sf.anathema.lib.gui.layout;

import net.miginfocom.layout.CC;

import javax.swing.JButton;

public class LayoutUtils {
  public static final int DEFAULT_COMPONENT_SPACING = 11;

  public static CC constraintsForImageButton(JButton button) {
    String width = String.valueOf(button.getPreferredSize().width);
    String height = String.valueOf(button.getPreferredSize().height);
    return new CC().maxWidth(width).maxHeight(height);
  }

  public static int getComponentSpacing() {
    return DEFAULT_COMPONENT_SPACING;
  }
}
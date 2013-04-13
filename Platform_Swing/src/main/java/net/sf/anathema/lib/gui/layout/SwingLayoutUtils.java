package net.sf.anathema.lib.gui.layout;

import net.miginfocom.layout.CC;

import javax.swing.JComponent;

public class SwingLayoutUtils {
  public static CC constraintsForImageButton(JComponent button) {
    String width = String.valueOf(button.getPreferredSize().width);
    String height = String.valueOf(button.getPreferredSize().height);
    return new CC().maxWidth(width).maxHeight(height);
  }
}

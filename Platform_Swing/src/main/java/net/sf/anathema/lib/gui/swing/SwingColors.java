package net.sf.anathema.lib.gui.swing;

import javax.swing.UIManager;
import java.awt.Color;

public class SwingColors {

  public static Color getControlHighlightColor() {
    return UIManager.getColor("controlHighlight");
  }

  public static Color getControlLtHighlightColor() {
    Color ltHighlightColor = UIManager.getColor("controlLtHighlight");
    if (ltHighlightColor == null) {
      //Happens with "Nimbus" L&F
      return getControlHighlightColor();
    }
    return ltHighlightColor;
  }

  public static Color getControlShadowColor() {
    return UIManager.getColor("controlShadow");
  }

  public static Color getTextAreaForegroundColor() {
    return UIManager.getColor("TextArea.foreground");
  }

  public static Color getTextAreaBackgroundColor() {
    return UIManager.getColor("TextArea.background");
  }

  public static Color getTextAreaSelectionBackgroundColor() {
    return UIManager.getColor("TextArea.selectionBackground");
  }

  public static Color getTextAreaSelectionForegroundColor() {
    return UIManager.getColor("TextArea.selectionForeground");
  }

  public static Color getTextAreaInactiveForegroundColor() {
    return UIManager.getColor("TextArea.inactiveForeground");
  }
}
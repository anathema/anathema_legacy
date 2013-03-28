package net.sf.anathema.lib.gui.swing;

import javax.swing.UIManager;
import java.awt.Color;

public class SwingColors {

  public static Color getControlHighlightColor() {
    return UIManager.getColor("controlHighlight"); //$NON-NLS-1$
  }

  public static Color getControlLtHighlightColor() {
    Color ltHighlightColor = UIManager.getColor("controlLtHighlight"); //$NON-NLS-1$
    if (ltHighlightColor == null) {
      //Happens with "Nimbus" L&F
      return getControlHighlightColor();
    }
    return ltHighlightColor;
  }

  public static Color getControlShadowColor() {
    return UIManager.getColor("controlShadow"); //$NON-NLS-1$
  }

  public static Color getTextAreaForegroundColor() {
    return UIManager.getColor("TextArea.foreground"); //$NON-NLS-1$
  }

  public static Color getTextAreaBackgroundColor() {
    return UIManager.getColor("TextArea.background"); //$NON-NLS-1$
  }

  public static Color getTextAreaSelectionBackgroundColor() {
    return UIManager.getColor("TextArea.selectionBackground"); //$NON-NLS-1$
  }

  public static Color getTextAreaSelectionForegroundColor() {
    return UIManager.getColor("TextArea.selectionForeground"); //$NON-NLS-1$
  }

  public static Color getTextAreaInactiveForegroundColor() {
    return UIManager.getColor("TextArea.inactiveForeground"); //$NON-NLS-1$
  }
}
package net.sf.anathema.lib.gui.swing;

import javax.swing.JComponent;
import javax.swing.LookAndFeel;

public class LookAndFeelUtilities {

  public static final String COMPONENT_TYPE_LABEL = "Label"; //$NON-NLS-1$

  public static void installColorsAndFont(JComponent component, String type) {
    LookAndFeel.installColorsAndFont(component, type + ".background", type + ".foreground", type + ".font");
  }

}
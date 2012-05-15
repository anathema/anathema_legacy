package net.sf.anathema.lib.gui.swing;

import javax.swing.JComponent;
import javax.swing.border.TitledBorder;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BorderUtilities {

  /** Sets the given titled border as border to the specified swing and assures the title
   * text to be visually enabled/disabled when the swing is enabled/disabled. */
  public static void attachDisableableTitledBorder(
      final JComponent component,
      final TitledBorder border) {
    component.setBorder(border);
    component.addPropertyChangeListener(
        GuiUtilities.ENABLED_PROPERTY_NAME,
        new PropertyChangeListener() {
          @Override
          public void propertyChange(PropertyChangeEvent evt) {
            BorderUtilities.setEnabled(border, component, component.isEnabled());
          }
        });
    BorderUtilities.setEnabled(border, component, component.isEnabled());
  }

  /**
   * Visually enables/disables a {@link javax.swing.border.TitledBorder}by changing the color of
   * its title text.
   */
  public static void setEnabled(
      TitledBorder border,
      JComponent ownerComponent,
      boolean enabled) {
    if (enabled) {
      border.setTitleColor(SwingColors.getTextAreaForegroundColor());
    }
    else {
      border.setTitleColor(SwingColors.getTextAreaInactiveForegroundColor());
    }
    ownerComponent.repaint();
  }
}
package net.sf.anathema.framework.view.toolbar;

import javax.swing.AbstractButton;
import java.awt.Insets;

public class ToolbarUtilities {
  private static final Insets TOOLBAR_BUTTON_MARGIN = new Insets(1, 1, 1, 1);
  public static void configureToolBarButton(AbstractButton button) {
    button.setFocusPainted(false);
    button.setMargin(TOOLBAR_BUTTON_MARGIN);
    if (button.getToolTipText() == null) {
      button.setToolTipText(button.getText());
    }
    if (button.getIcon() != null) {
      button.setText(null);
    }
  }
}

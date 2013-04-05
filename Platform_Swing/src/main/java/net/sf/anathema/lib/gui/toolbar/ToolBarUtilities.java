package net.sf.anathema.lib.gui.toolbar;

import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Insets;

public class ToolBarUtilities {

  private static final Insets TOOLBAR_BUTTON_MARGIN = new Insets(1, 1, 1, 1);

  public static AbstractButton createToolBarButton(Action action) {
    AbstractButton button = createButton(action);
    configureToolBarButton(button);
    return button;
  }

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

  private static AbstractButton createButton(Action action) {
    if (action instanceof SmartToggleAction) {
      return ActionWidgetFactory.createToggleButton((SmartToggleAction) action);
    } else {
      return new JButton(action);
    }
  }
}
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
    return createToolBarButton(action, new DefaultToolBarButtonConfiguration());
  }

  public static AbstractButton createToolBarButton(Action action, IToolBarButtonConfiguration configuration) {
    AbstractButton button;
    if (action instanceof SmartToggleAction) {
      button = ActionWidgetFactory.createToggleButton((SmartToggleAction) action);
    } else {
      button = new JButton();
    }
    button.setAction(action);
    configureToolBarButton(button, configuration);
    return button;
  }

  public static void configureToolBarButton(AbstractButton button) {
    configureToolBarButton(button, new DefaultToolBarButtonConfiguration());
  }

  public static void configureToolBarButton(AbstractButton button, IToolBarButtonConfiguration configuration) {
    button.setFocusPainted(configuration.isFocusPainted());
    button.setMargin(TOOLBAR_BUTTON_MARGIN);

    if (button.getToolTipText() == null) {
      button.setToolTipText(button.getText());
    }
    if (button.getIcon() != null) {
      button.setText(null);
    }
  }
}
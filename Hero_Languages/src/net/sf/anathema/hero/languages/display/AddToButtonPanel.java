package net.sf.anathema.hero.languages.display;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Insets;

public class AddToButtonPanel implements AddToSwingComponent {
  private static final Insets TOOLBAR_BUTTON_MARGIN = new Insets(1, 1, 1, 1);
  private JPanel panel;

  public AddToButtonPanel(JPanel panel) {
    this.panel = panel;
  }

  @Override
  public void add(Action action) {
    panel.add(createToolBarButton(action));
  }


  private AbstractButton createToolBarButton(Action action) {
    AbstractButton button = createButton(action);
    configureToolBarButton(button);
    return button;
  }

  private void configureToolBarButton(AbstractButton button) {
    button.setFocusPainted(false);
    button.setMargin(TOOLBAR_BUTTON_MARGIN);
    if (button.getToolTipText() == null) {
      button.setToolTipText(button.getText());
    }
    if (button.getIcon() != null) {
      button.setText(null);
    }
  }

  private AbstractButton createButton(Action action) {
    if (action instanceof SmartToggleAction) {
      return ActionWidgetFactory.createToggleButton((SmartToggleAction) action);
    } else {
      return new JButton(action);
    }
  }
}
package net.sf.anathema.hero.languages.display;

import net.sf.anathema.framework.view.menu.AddToSwingComponent;
import net.sf.anathema.lib.gui.action.ActionWidgetFactory;
import net.sf.anathema.lib.gui.action.SmartToggleAction;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddToButtonPanel implements AddToSwingComponent {
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
    ToolbarUtilities.configureToolBarButton(button);
    return button;
  }

  private AbstractButton createButton(Action action) {
    if (action instanceof SmartToggleAction) {
      return ActionWidgetFactory.createToggleButton((SmartToggleAction) action);
    } else {
      return new JButton(action);
    }
  }
}
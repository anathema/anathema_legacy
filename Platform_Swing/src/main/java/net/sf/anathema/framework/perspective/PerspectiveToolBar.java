package net.sf.anathema.framework.perspective;

import net.sf.anathema.framework.view.toolbar.ToolBarButton;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import java.awt.Component;
import java.awt.Dimension;

public class PerspectiveToolBar implements ToolBar, IView {

  private JToolBar toolBar;

  public PerspectiveToolBar() {
    this.toolBar = new JToolBar();
    this.toolBar.setFloatable(false);
    this.toolBar.setRollover(true);
  }

  @Override
  public JToolBar getComponent() {
    return toolBar;
  }

  @Override
  public void addTools(Action... toolBarActions) {
    for (Action action : toolBarActions) {
      addComponent(new ToolBarButton(), action);
    }
  }

  @Override
  public void addMenu(Icon buttonIcon, Action[] menuActions, String toolTip) {
    final ToolBarButton button = new ToolBarButton();
    final JPopupMenu menu = new JPopupMenu();
    for (Action action : menuActions) {
      menu.add(action);
    }
    SmartAction action = new SmartAction(buttonIcon) {
      @Override
      protected void execute(Component parentComponent) {
        menu.show(button, 0, button.getHeight());
      }
    };
    action.setToolTipText(toolTip);
    addComponent(button, action);
  }

  @Override
  public void add(JComponent component) {
    toolBar.add(component);
  }

  private void addComponent(JButton button, Action action) {
    button.setAction(action);
    toolBar.add(button);
    setButtonSize(button);
  }

  private void setButtonSize(JButton button) {
    Dimension dimension = button.getPreferredSize();
    button.setMinimumSize(dimension);
    button.setSize(dimension);
  }
}
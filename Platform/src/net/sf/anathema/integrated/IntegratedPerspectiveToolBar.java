package net.sf.anathema.integrated;

import net.sf.anathema.framework.view.toolbar.ToolBarButton;
import net.sf.anathema.lib.gui.IView;
import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import java.awt.Component;
import java.awt.Dimension;

public class IntegratedPerspectiveToolBar implements ToolBar, IView {

  private JToolBar toolBar;

  public IntegratedPerspectiveToolBar() {
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
    final JPopupMenu menu = new JPopupMenu();
    for (Action action : menuActions) {
      menu.add(action);
    }
    final ToolBarButton button = new ToolBarButton();
    SmartAction action = new SmartAction(buttonIcon) {
	  @Override
      protected void execute(Component parentComponent) {
        menu.show(button, 0, button.getHeight());
      }
    };
    action.setToolTipText(toolTip);
    addComponent(button, action);
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
package net.sf.anathema.framework.view;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public class AnathemaToolBar implements IAnathemaToolbar {

  private JToolBar toolBar;

  public AnathemaToolBar() {
    this.toolBar = new JToolBar();
    this.toolBar.setFloatable(false);
  }

  public JToolBar getComponent() {
    return toolBar;
  }

  public void addTools(Action... toolBarActions) {
    toolBar.addSeparator();
    for (int i = 0; i < toolBarActions.length; i++) {
      Action action = toolBarActions[i];
      JButton button = toolBar.add(action);
      button.setPreferredSize(new Dimension(24, 24));
    }
  }
}
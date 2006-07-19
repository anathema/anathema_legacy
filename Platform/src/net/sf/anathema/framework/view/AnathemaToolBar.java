package net.sf.anathema.framework.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public class AnathemaToolBar implements IAnathemaToolbar {

  private JToolBar toolBar;

  public AnathemaToolBar() {
    this.toolBar = new JToolBar();
    this.toolBar.setFloatable(false);
//    this.toolBar.setRollover(true);
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

  public void addMenu(Icon buttonIcon, Action... menuActions) {
    final JButton button = new JButton(buttonIcon);
    final JPopupMenu menu = new JPopupMenu();
    for (Action action : menuActions) {
      menu.add(action);
    }
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        menu.show(button, 0, button.getHeight());
      }
    });
    button.add(menu);
    toolBar.add(button);
  }
}
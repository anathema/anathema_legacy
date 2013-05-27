package net.sf.anathema.framework.view.menu;

import javax.swing.Action;
import javax.swing.JMenu;

public class AddToSwingMenu implements AddToSwingComponent {
  private JMenu menu;

  public AddToSwingMenu(JMenu menu) {
    this.menu = menu;
  }

  @Override
  public void add(Action action) {
    menu.add(action);
  }
}
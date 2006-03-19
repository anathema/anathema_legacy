package net.sf.anathema.framework.presenter.action;

import javax.swing.Action;
import javax.swing.JMenu;

import net.sf.anathema.framework.presenter.menu.IMenuItem;

public class ActionMenuItem implements IMenuItem {

  private final Action action;

  public ActionMenuItem(Action action) {
    this.action = action;
  }

  public void addToMenu(JMenu menu) {
    menu.add(action);
  }
}
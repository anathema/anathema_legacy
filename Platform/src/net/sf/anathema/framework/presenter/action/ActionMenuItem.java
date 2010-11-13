package net.sf.anathema.framework.presenter.action;

import javax.swing.Action;

import net.sf.anathema.framework.presenter.menu.IMenuItem;
import net.sf.anathema.framework.view.menu.IMenu;

public class ActionMenuItem implements IMenuItem {

  private final Action action;

  public ActionMenuItem(Action action) {
    this.action = action;
  }

  public void addToMenu(IMenu menu) {
    menu.addMenuItem(action);
  }
}
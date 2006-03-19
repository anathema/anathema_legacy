package net.sf.anathema.framework.presenter.action;

import javax.swing.JMenu;

import net.sf.anathema.framework.presenter.menu.IMenuItem;

public class MenuMenuItem implements IMenuItem {

  private JMenu menu;

  public MenuMenuItem(JMenu menu) {
    this.menu = menu;
  }

  public void addToMenu(JMenu parent) {
    parent.add(menu);
  }
}
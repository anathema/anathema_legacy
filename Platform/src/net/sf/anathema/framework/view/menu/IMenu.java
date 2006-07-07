package net.sf.anathema.framework.view.menu;

import javax.swing.Action;
import javax.swing.JMenuItem;

public interface IMenu {

  public void addSubMenu(String menuName, Action[] actions);

  public void addMenuItem(Action action);

  public void addMenuItem(JMenuItem menuItem);

  public void addSeparator();
}
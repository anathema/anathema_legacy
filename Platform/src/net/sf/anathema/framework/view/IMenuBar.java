package net.sf.anathema.framework.view;

import javax.swing.JMenu;

import net.sf.anathema.framework.view.menu.IMenu;

public interface IMenuBar {

  public IMenu getMainMenu();

  public IMenu getHelpMenu();

  public void addMenu(JMenu menu);
}
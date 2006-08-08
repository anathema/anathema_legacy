package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.menu.IMenu;

public interface IMenuBar {

  public IMenu getMainMenu();

  public IMenu getHelpMenu();

  public IMenu addMenu(String title);
}
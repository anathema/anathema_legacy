package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.menu.IMenu;

public interface MenuBar {

  IMenu getMainMenu();

  IMenu getHelpMenu();

  IMenu addMenu(String title);
}
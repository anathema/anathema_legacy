package net.sf.anathema.view;

import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.framework.view.menu.IMenu;

public class NullMenuBar implements MenuBar {
  @Override
  public IMenu getMainMenu() {
    return new NullMenu();
  }

  @Override
  public IMenu getHelpMenu() {
    return new NullMenu();
  }
}
package net.sf.anathema.view;

import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.menu.IMenu;

public class NullMenuBar implements IMenuBar {
  @Override
  public IMenu getMainMenu() {
    return new NullMenu();
  }

  @Override
  public IMenu getHelpMenu() {
    return new NullMenu();
  }

  @Override
  public IMenu addMenu(String title) {
    return new NullMenu();
  }
}
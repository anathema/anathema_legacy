package net.sf.anathema.view;

import net.sf.anathema.framework.view.menu.IMenu;

import javax.swing.Action;

public class NullMenu implements IMenu {
  @Override
  public void addMenuItem(Action action) {
    //nothing to do
  }

  @Override
  public void addSeparator() {
    //nothing to do
  }

}
package net.sf.anathema.view;

import net.sf.anathema.framework.view.menu.IMenu;
import net.sf.anathema.interaction.Command;

public class NullMenu implements IMenu {
  @Override
  public void addMenuItem(Command action, String label) {
    //nothing to do
  }

  @Override
  public void addSeparator() {
    //nothing to do
  }

}
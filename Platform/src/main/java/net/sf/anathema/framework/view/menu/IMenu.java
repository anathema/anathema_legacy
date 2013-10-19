package net.sf.anathema.framework.view.menu;

import net.sf.anathema.interaction.Command;

public interface IMenu {

  void addMenuItem(Command action, String label);
}
package net.sf.anathema.framework.view.menu;

import javax.swing.Action;

public interface IMenu {

  void addMenuItem(Action action);

  void addSeparator();

  void setMnemonic(char c);
}
package net.sf.anathema.framework.view.menu;

import javax.swing.Action;

public interface IMenu {

  public void addMenuItem(Action action);

  public void addSeparator();

  public void setMnemonic(char c);
}
package net.sf.anathema.framework.view;

import javax.swing.JMenu;

import net.sf.anathema.framework.view.menu.IMenuBar;

public interface IAnathemaMenu {

  public IMenuBar getMainMenu();

  public IMenuBar getHelpMenu();

  public void addMenu(JMenu menu);
}
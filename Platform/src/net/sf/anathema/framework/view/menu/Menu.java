package net.sf.anathema.framework.view.menu;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu implements IMenuBarView {

  private final JMenu menu;

  public Menu(String name) {
    menu = new JMenu(name);
  }

  public Menu(String name, char mnemonic) {
    this(name);
    menu.setMnemonic(mnemonic);
  }

  public JMenu getComponent() {
    return menu;
  }

  public void addSubMenu(String menuName, Action[] actions) {
    JMenu subMenu = new JMenu(menuName);
    for (Action action : actions) {
      subMenu.add(action);
    }
    addMenuItem(subMenu);
  }

  public void addMenuItem(Action action) {
    menu.add(action);
  }

  public void addSeparator() {
    menu.addSeparator();
  }

  public void addMenuItem(JMenuItem menuItem) {
    menu.add(menuItem);
  }
}
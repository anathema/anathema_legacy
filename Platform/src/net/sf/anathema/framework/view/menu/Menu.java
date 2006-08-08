package net.sf.anathema.framework.view.menu;

import javax.swing.Action;
import javax.swing.JMenu;

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

  public void addMenuItem(Action action) {
    menu.add(action);
  }

  public void addSeparator() {
    menu.addSeparator();
  }

  public void setMnemonic(char c) {
    menu.setMnemonic(c);
  }
}
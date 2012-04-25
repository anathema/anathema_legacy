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

  @Override
  public JMenu getComponent() {
    return menu;
  }

  @Override
  public void addMenuItem(Action action) {
    menu.add(action);
  }

  @Override
  public void addSeparator() {
    menu.addSeparator();
  }

  @Override
  public void setMnemonic(char c) {
    menu.setMnemonic(c);
  }
}
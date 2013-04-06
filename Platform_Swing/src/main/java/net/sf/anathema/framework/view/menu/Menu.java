package net.sf.anathema.framework.view.menu;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.SwingActionTool;

import javax.swing.JMenu;

public class Menu implements IMenu {

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

  @Override
  public void addMenuItem(Command action, String label) {
    SwingActionTool tool = new SwingActionTool();
    tool.setText(label);
    tool.setCommand(action);
    menu.add(tool.getAction());
  }

  @Override
  public void addSeparator() {
    menu.addSeparator();
  }
}
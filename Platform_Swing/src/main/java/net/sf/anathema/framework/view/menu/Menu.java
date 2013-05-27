package net.sf.anathema.framework.view.menu;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.swing.interaction.ActionInteraction;

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
    ActionInteraction tool = new ActionInteraction();
    tool.setText(label);
    tool.setCommand(action);
    tool.addTo(new AddToSwingMenu(menu));
  }

  @Override
  public void addSeparator() {
    menu.addSeparator();
  }
}
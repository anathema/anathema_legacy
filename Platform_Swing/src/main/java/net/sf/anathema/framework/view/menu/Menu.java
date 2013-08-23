package net.sf.anathema.framework.view.menu;

import javafx.scene.control.SeparatorMenuItem;
import net.sf.anathema.interaction.Command;

public class Menu implements IMenu {

  private final javafx.scene.control.Menu menu;

  public Menu(String name) {
    menu = new javafx.scene.control.Menu(name);
  }

  public Menu(String name, char mnemonic) {
    this(name);
  }

  public javafx.scene.control.Menu getNode() {
    return menu;
  }

  @Override
  public void addMenuItem(Command action, String label) {
    FxMenuTool tool = new FxMenuTool();
    tool.setText(label);
    tool.setCommand(action);
    menu.getItems().add(tool.getNode());
  }

  @Override
  public void addSeparator() {
    menu.getItems().add(new SeparatorMenuItem());
  }
}
package net.sf.anathema.framework.view.menu;

import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import net.sf.anathema.interaction.Command;

import static javafx.scene.input.KeyCombination.SHORTCUT_DOWN;

public class Menu implements IMenu {

  private final javafx.scene.control.Menu menu;

  public Menu(String name) {
    menu = new javafx.scene.control.Menu(name);
  }

  public Menu(String name, char mnemonic) {
    this(name);
    KeyCode keyCode = KeyCode.valueOf(String.valueOf(mnemonic));
    KeyCodeCombination keyCodeCombination = new KeyCodeCombination(keyCode, SHORTCUT_DOWN);
    menu.setAccelerator(keyCodeCombination);
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
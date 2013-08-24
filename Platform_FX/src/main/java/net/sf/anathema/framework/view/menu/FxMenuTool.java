package net.sf.anathema.framework.view.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.platform.tool.ImageContainer;
import net.sf.anathema.platform.tool.LoadImage;

import static javafx.scene.input.KeyCombination.SHORTCUT_DOWN;

public class FxMenuTool implements Tool {

  private final MenuItem item = new MenuItem();
  private final CommandProxy command = new CommandProxy();

  public FxMenuTool() {
    item.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        command.execute();
      }
    });
  }

  @Override
  public void setIcon(RelativePath relativePath) {
    ImageContainer container = new LoadImage(relativePath).run();
    ImageView imageView = new ImageView();
    container.displayIn(imageView);
    item.setGraphic(imageView);
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    //nothing to do
  }

  @Override
  public void setTooltip(String text) {
    //menuitems in fx can't have tooltips
  }

  @Override
  public void setText(String text) {
    item.setText(text);
  }

  @Override
  public void enable() {
    item.setDisable(false);
  }

  @Override
  public void disable() {
    item.setDisable(true);
  }

  @Override
  public void setCommand(Command command) {
    this.command.setDelegate(command);
  }

  @Override
  public void setHotkey(Hotkey shortcut) {
    KeyCode keyCode = KeyCode.valueOf(String.valueOf(shortcut.asString()));
    KeyCodeCombination keyCodeCombination = new KeyCodeCombination(keyCode, SHORTCUT_DOWN);
    item.setAccelerator(keyCodeCombination);
  }

  public MenuItem getNode() {
    return item;
  }
}
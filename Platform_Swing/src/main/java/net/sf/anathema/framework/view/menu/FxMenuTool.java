package net.sf.anathema.framework.view.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.interaction.CommandProxy;
import net.sf.anathema.interaction.Hotkey;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;

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
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setOverlay(RelativePath relativePath) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setTooltip(String text) {
    //To change body of implemented methods use File | Settings | File Templates.
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
  public void setHotkey(Hotkey s) {

  }

  public MenuItem getNode() {
    return item;
  }
}
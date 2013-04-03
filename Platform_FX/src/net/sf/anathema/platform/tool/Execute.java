package net.sf.anathema.platform.tool;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import net.sf.anathema.interaction.Command;

public class Execute implements EventHandler<ActionEvent> {
  private final Command command;

  public Execute(Command command) {
    this.command = command;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    command.execute();
  }
}
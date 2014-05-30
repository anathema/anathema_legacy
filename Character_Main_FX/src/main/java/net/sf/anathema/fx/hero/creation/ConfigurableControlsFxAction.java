package net.sf.anathema.fx.hero.creation;

import javafx.event.ActionEvent;
import net.sf.anathema.interaction.Command;
import org.controlsfx.control.action.AbstractAction;

public class ConfigurableControlsFxAction extends AbstractAction {
  private Command command;

  public ConfigurableControlsFxAction(String text) {
    super(text);
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  @Override
  public void handle(ActionEvent actionEvent) {
    command.execute();
  }
}

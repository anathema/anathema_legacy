package net.sf.anathema.fx.hero.creation;

import javafx.event.ActionEvent;
import net.sf.anathema.interaction.Command;
import org.controlsfx.control.action.AbstractAction;

public class ConfigurableControlsFxAction extends AbstractAction {
  private Command command;

  public ConfigurableControlsFxAction(String text) {
    super(text);
  }

  @Override
  public void execute(ActionEvent ae) {
    command.execute();
  }

  public void setCommand(Command command) {
    this.command = command;
  }
}

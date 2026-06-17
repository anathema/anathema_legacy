package net.sf.anathema.fx.hero.creation;

import net.sf.anathema.interaction.Command;
import org.controlsfx.control.action.Action;

public class ConfigurableControlsFxAction extends Action {
  private Command command;

  public ConfigurableControlsFxAction(String text) {
    super(text);
  }

  // Action.handle(ActionEvent) is final in ControlsFX 8.40+, so behaviour is supplied through the
  // event-handler consumer rather than by overriding handle.
  public void setCommand(Command command) {
    this.command = command;
    setEventHandler(actionEvent -> command.execute());
  }
}

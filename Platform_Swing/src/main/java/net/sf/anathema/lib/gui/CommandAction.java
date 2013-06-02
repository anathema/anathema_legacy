package net.sf.anathema.lib.gui;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.Icon;
import java.awt.Component;

public class CommandAction extends SmartAction {
  private Command command;

  public CommandAction(Command command) {
    this.command = command;
  }

  public CommandAction(Command command, Icon buttonIcon) {
    super(buttonIcon);
    this.command = command;
  }

  @Override
  protected void execute(Component parentComponent) {
    command.execute();
  }
}
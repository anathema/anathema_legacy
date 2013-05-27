package net.sf.anathema.lib.gui;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.gui.action.SmartAction;

import java.awt.Component;

public class CommandAction extends SmartAction {
  private Command command;

  public CommandAction(Command command) {
    this.command = command;
  }

  @Override
  protected void execute(Component parentComponent) {
    command.execute();
  }
}
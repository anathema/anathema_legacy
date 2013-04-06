package net.sf.anathema.framework.presenter.action;

import net.sf.anathema.interaction.Command;

public class AnathemaExitAction implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }
}
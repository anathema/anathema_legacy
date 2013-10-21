package net.sf.anathema.framework.presenter.action.exit;

import net.sf.anathema.interaction.Command;

public class AnathemaExitAction implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }
}
package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.interaction.Command;

public class Allower implements Vetor {

  @Override
  public void requestPermissionFor(Command command) {
    command.execute();
  }
}
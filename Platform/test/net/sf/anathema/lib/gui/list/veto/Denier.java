package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.interaction.Command;

public class Denier implements Vetor {

  @Override
  public void requestPermissionFor(Command command) {
    //nothing to do, permission denied
  }
}
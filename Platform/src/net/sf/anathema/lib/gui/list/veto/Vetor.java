package net.sf.anathema.lib.gui.list.veto;

import net.sf.anathema.interaction.Command;

public interface Vetor {

  boolean vetos();

  void requestPermissionFor(Command command);
}
package net.sf.anathema.character.perspective.model;

import net.sf.anathema.lib.control.IChangeListener;

import java.io.IOException;

public interface ItemSelectionModel {
  void whenCurrentSelectionBecomesDirty(IChangeListener listener);

  void whenCurrentSelectionBecomesClean(IChangeListener listener);

  void saveCurrent() throws IOException;
}

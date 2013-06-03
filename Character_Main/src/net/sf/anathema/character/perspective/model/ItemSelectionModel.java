package net.sf.anathema.character.perspective.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.Resources;

import java.io.IOException;

public interface ItemSelectionModel {

  void saveCurrent() throws IOException;

  void whenCurrentSelectionBecomesDirty(IChangeListener listener);

  void whenCurrentSelectionBecomesClean(IChangeListener listener);

  void whenGetsSelection(IChangeListener listener);

  void whenCurrentSelectionBecomesExperienced(IChangeListener listener);

  void whenCurrentSelectionBecomesInexperienced(IChangeListener listener);

  void convertCurrentToExperienced();

  void printCurrentItemQuickly(Resources resources);

  void printCurrentItemControlled(Resources resources);

  void createNew(Resources resources);

  void whenNewCharacterIsAdded(NewCharacterListener listener);
}
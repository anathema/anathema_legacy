package net.sf.anathema.character.perspective.model.model;

import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.resources.IResources;

import java.io.IOException;

public interface ItemSelectionModel {

  void saveCurrent() throws IOException;

  void whenCurrentSelectionBecomesDirty(IChangeListener listener);

  void whenCurrentSelectionBecomesClean(IChangeListener listener);

  void whenGetsSelection(IChangeListener listener);

  void whenCurrentSelectionBecomesExperienced(IChangeListener listener);

  void whenCurrentSelectionBecomesInexperienced(IChangeListener listener);

  void convertCurrentToExperienced();

  void printCurrentItemQuickly(IResources resources);

  void printCurrentItemControlled(IResources resources);

  void createNew(IResources resources);

  void whenNewCharacterIsAdded(NewCharacterListener listener);
}

package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.selection.IStringEntryTraitModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IIntimaciesModel extends IStringEntryTraitModel<IIntimacy> {

  int getFreeIntimacies();

  int getCompletionValue();

  int getIntimaciesLimit();

  void addModelChangeListener(IChangeListener listener);

  boolean isCharacterExperienced();
}
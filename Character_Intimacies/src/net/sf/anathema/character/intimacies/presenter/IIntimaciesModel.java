package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.selection.IStringEntryTraitModel;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IIntimaciesModel extends IStringEntryTraitModel<IIntimacy> {

  public int getFreeIntimacies();

  public int getCompletionValue();

  public int getIntimaciesLimit();

  public void addModelChangeListener(IChangeListener listener);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}
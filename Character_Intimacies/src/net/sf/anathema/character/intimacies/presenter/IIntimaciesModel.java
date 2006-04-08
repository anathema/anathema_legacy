package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;
import net.sf.anathema.lib.control.IChangeListener;

public interface IIntimaciesModel extends IRemovableEntryModel<IIntimacy> {

  public int getFreeIntimacies();

  public void setCurrentName(String name);

  public int getCompletionValue();

  public int getIntimaciesLimit();

  public void addModelChangeListener(IChangeListener listener);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}
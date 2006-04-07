package net.sf.anathema.character.intimacies.presenter;

import net.sf.anathema.character.intimacies.model.IIntimacy;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;

public interface IIntimaciesModel extends IRemovableEntryModel<IIntimacy> {

  public int getFreeIntimacies();

  public void setCurrentName(String name);
}
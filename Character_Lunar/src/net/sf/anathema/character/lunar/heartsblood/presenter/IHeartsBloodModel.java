package net.sf.anathema.character.lunar.heartsblood.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;

public interface IHeartsBloodModel extends IRemovableEntryModel<IAnimalForm> {

  public void setCurrentName(String newValue);

  public void setCurrentStrength(int newValue);

  public void setCurrentStamina(int newValue);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}
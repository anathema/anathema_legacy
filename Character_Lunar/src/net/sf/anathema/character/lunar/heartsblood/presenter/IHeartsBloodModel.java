package net.sf.anathema.character.lunar.heartsblood.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryModel;

public interface IHeartsBloodModel extends IRemovableEntryModel<IAnimalForm> {

  void setCurrentName(String newValue);

  void setCurrentStrength(int newValue);

  void setCurrentStamina(int newValue);
  
  void setCurrentDexterity(int newValue);
  
  void setCurrentAppearance(int newValue);

  void addCharacterChangeListener(ICharacterChangeListener listener);
}
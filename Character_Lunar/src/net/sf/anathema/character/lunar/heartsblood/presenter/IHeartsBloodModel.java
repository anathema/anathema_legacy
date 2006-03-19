package net.sf.anathema.character.lunar.heartsblood.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ICharacterChangeListener;

public interface IHeartsBloodModel extends IAdditionalModel {

  public IAnimalForm[] getAnimalForms();

  public IAnimalForm addAnimalForm(String name, int strength, int stamina);

  public void setCurrentName(String newValue);

  public void setCurrentStrength(int newValue);

  public void setCurrentStamina(int newValue);

  public void commitSelection();

  public void removeSelection(IAnimalForm form);

  public void addModelChangeListener(IHeartsBloodModelListener listener);

  public void addCharacterChangeListener(ICharacterChangeListener listener);
}
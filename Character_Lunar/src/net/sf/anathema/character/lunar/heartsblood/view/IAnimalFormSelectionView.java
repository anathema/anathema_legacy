package net.sf.anathema.character.lunar.heartsblood.view;

import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;

import java.awt.event.ActionListener;

public interface IAnimalFormSelectionView {

  void addNameListener(ObjectValueListener<String> listener);

  void addStrengthListener(IIntValueChangedListener listener);
  
  void addDexterityListener(IIntValueChangedListener listener);

  void addStaminaListener(IIntValueChangedListener listener);
  
  void addAppearanceListener(IIntValueChangedListener listener);

  void addAddButtonListener(ActionListener listener);

  void setAddButtonEnabled(boolean complete);

  void setName(String name);

  void setStrength(int value);
  
  void setDexterity(int value);

  void setStamina(int value);
  
  void setAppearance(int value);

}

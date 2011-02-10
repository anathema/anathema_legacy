package net.sf.anathema.character.lunar.heartsblood.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface IAnimalFormSelectionView {

  public void addNameListener(IObjectValueChangedListener<String> listener);

  public void addStrengthListener(IIntValueChangedListener listener);
  
  public void addDexterityListener(IIntValueChangedListener listener);

  public void addStaminaListener(IIntValueChangedListener listener);
  
  public void addAppearanceListener(IIntValueChangedListener listener);

  public void addAddButtonListener(ActionListener listener);

  public void setAddButtonEnabled(boolean complete);

  public void setName(String name);

  public void setStrength(int value);
  
  public void setDexterity(int value);

  public void setStamina(int value);
  
  public void setAppearance(int value);

}

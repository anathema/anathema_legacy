package net.sf.anathema.character.lunar.heartsblood.view;

import java.awt.event.ActionListener;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.stringvalue.IStringValueChangedListener;

public interface IAnimalFormSelectionView {

  public void addNameListener(IStringValueChangedListener listener);

  public void addStrengthListener(IIntValueChangedListener listener);

  public void addStaminaListener(IIntValueChangedListener listener);

  public void addAddButtonListener(ActionListener listener);

  public void setAddButtonEnabled(boolean complete);

  public void setName(String name);

  public void setStrength(int value);

  public void setStamina(int value);

}

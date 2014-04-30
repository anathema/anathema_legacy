package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.lib.control.IntValueChangedListener;

public interface IIntegerSpinner {
  void setValue(int newValue);

  void setMinimum(Integer minimum);
  
  void setMaximum(Integer maximum);

  void addChangeListener(IntValueChangedListener listener);

  void setEnabled(boolean enabled);
}

package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public interface IToggleButtonView {
  public void addButtonSelectedListener(IBooleanValueChangedListener listener);

  public void setButtonState(boolean selected, boolean enabled);
}
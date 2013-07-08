package net.sf.anathema.framework.value;

import net.sf.anathema.lib.control.IntValueChangedListener;

public interface IntValueView {

  void setValue(int newValue);
  
  void addIntValueChangedListener(IntValueChangedListener listener);

  void removeIntValueChangedListener(IntValueChangedListener listener);
}
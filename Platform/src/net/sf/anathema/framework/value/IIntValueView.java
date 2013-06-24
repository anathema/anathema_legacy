package net.sf.anathema.framework.value;

import net.sf.anathema.lib.control.IIntValueChangedListener;

public interface IIntValueView {

  void setValue(int newValue);
  
  void addIntValueChangedListener(IIntValueChangedListener listener);

  void removeIntValueChangedListener(IIntValueChangedListener listener);
}
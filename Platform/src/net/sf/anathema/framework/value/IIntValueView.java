package net.sf.anathema.framework.value;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IIntValueView {

  public void setValue(int newValue);
  
  public void addIntValueChangedListener(IIntValueChangedListener listener);

  public void removeIntValueChangedListener(IIntValueChangedListener listener);

  public void setMaximum(int maximalValue);
}
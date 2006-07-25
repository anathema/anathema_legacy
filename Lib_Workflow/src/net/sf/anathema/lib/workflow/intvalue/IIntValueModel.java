package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IIntValueModel {
  
  public Integer getMinimum();
  
  public Integer getMaximum();

  public void setValue(int value);
  
  public int getValue();
  
  public void addIntValueChangeListener(IIntValueChangedListener changeListener);
}
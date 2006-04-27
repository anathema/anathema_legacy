package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IIntValueModel {

  public void setValue(int value);
  
  public int getValue();
  
  public void addIntValueChangeListener(IIntValueChangedListener changeListener);
}
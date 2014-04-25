package net.sf.anathema.lib.workflow.intvalue;

import net.sf.anathema.lib.control.IntValueChangedListener;

public interface IIntValueModel {

  Integer getMinimum();

  Integer getMaximum();

  void setValue(int value);

  int getValue();

  void addIntValueChangeListener(IntValueChangedListener changeListener);
}
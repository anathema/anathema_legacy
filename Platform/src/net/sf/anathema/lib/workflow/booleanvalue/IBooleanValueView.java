package net.sf.anathema.lib.workflow.booleanvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;

public interface IBooleanValueView {

  void setSelected(boolean selected);

  void addChangeListener(IBooleanValueChangedListener listener);
}
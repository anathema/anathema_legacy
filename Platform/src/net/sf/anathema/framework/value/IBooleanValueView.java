package net.sf.anathema.framework.value;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public interface IBooleanValueView {

  public void setSelected(boolean selected);

  public void addChangeListener(IBooleanValueChangedListener listener);

}

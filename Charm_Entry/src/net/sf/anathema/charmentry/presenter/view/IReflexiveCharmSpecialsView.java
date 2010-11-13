package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface IReflexiveCharmSpecialsView {

  public void addStepListener(IIntValueChangedListener listener);

  public void addDefenseStepListener(IIntValueChangedListener listener);

  public void addSplitListener(IBooleanValueChangedListener listener);

  public void setEnabled(boolean enabled);

}

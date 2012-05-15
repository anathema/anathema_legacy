package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public interface IReflexiveCharmSpecialsView {

  void addStepListener(IIntValueChangedListener listener);

  void addDefenseStepListener(IIntValueChangedListener listener);

  void addSplitListener(IBooleanValueChangedListener listener);

  void setEnabled(boolean enabled);

}

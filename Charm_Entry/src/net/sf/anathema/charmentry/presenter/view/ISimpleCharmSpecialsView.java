package net.sf.anathema.charmentry.presenter.view;

import net.sf.anathema.lib.control.IIntValueChangedListener;

public interface ISimpleCharmSpecialsView {

  void addSpeedValueChangedListener(IIntValueChangedListener listener);

  void addDefenseValueChangedListener(IIntValueChangedListener listener);

  void setEnabled(boolean enabled);

}

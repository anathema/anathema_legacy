package net.sf.anathema.charmentry.demo;

import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;

public interface ISimpleCharmSpecialsView {

  public void addSpeedValueChangedListener(IIntValueChangedListener listener);

  public void addDefenseValueChangedListener(IIntValueChangedListener listener);

  public void setEnabled(boolean enabled);

}

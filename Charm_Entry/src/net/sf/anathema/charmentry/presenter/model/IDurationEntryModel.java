package net.sf.anathema.charmentry.presenter.model;

import net.sf.anathema.character.generic.magic.charms.Duration;
import net.sf.anathema.lib.control.change.IChangeListener;

public interface IDurationEntryModel {

  public String[] getDurations();

  public void setDuration(String newValue);

  public Duration getDuration();

  public void addModelListener(IChangeListener listener);
}
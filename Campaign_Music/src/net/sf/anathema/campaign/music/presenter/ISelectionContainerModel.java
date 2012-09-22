package net.sf.anathema.campaign.music.presenter;

import net.sf.anathema.lib.control.IChangeListener;

public interface ISelectionContainerModel<V> {

  V[] getAllAvailableValues();

  void setSelectedValues(V[] values);

  V[] getSelectedValues();

  void addChangeListener(IChangeListener listener);

  void removeChangeListener(IChangeListener listener);
}
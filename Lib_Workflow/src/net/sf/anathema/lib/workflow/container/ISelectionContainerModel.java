package net.sf.anathema.lib.workflow.container;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISelectionContainerModel<V> {

  public V[] getAllAvailableValues();

  public void setSelectedValues(V[] values);

  public V[] getSelectedValues();

  public void addChangeListener(IChangeListener listener);

  public void removeChangeListener(IChangeListener listener);
}
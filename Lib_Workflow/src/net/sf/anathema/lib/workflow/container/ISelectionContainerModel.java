package net.sf.anathema.lib.workflow.container;

import javax.swing.event.ChangeListener;

public interface ISelectionContainerModel<V> {

  public V[] getAllAvailableValues();

  public void setSelectedValues(V[] values);

  public V[] getSelectedValues();

  public void addChangeListener(ChangeListener listener);

  public void removeChangeListener(ChangeListener listener);
}
package net.sf.anathema.lib.workflow.container;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface ISelectionContainerView<V> {

  public void populate(V[] contentValues);

  public void setSelectedValues(V[] selectedValues);

  public void addSelectionChangeListener(IChangeListener listener);

  public V[] getSelectedValues();
}
package net.sf.anathema.lib.workflow.container;

import net.sf.anathema.lib.control.IChangeListener;

public interface ISelectionContainerView<V> {

  void populate(V[] contentValues);

  void setSelectedValues(V[] selectedValues);

  void addSelectionChangeListener(IChangeListener listener);

  V[] getSelectedValues();
}
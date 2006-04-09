package net.sf.anathema.lib.workflow.container;

import net.sf.anathema.lib.control.IChangeListener;

public interface ISelectionContainerView {

  public void populate(Object[] contentValues);

  public void setSelectedValues(Object[] selectedValues);

  public void addSelectionChangeListener(IChangeListener listener);

  public Object[] getSelectedValues();
}
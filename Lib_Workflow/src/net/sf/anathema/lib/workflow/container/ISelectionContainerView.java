package net.sf.anathema.lib.workflow.container;

import javax.swing.event.ChangeListener;

public interface ISelectionContainerView {

  public void populate(Object[] contentValues);

  public void setSelectedValues(Object[] selectedValues);

  public void addSelectionChangeListener(ChangeListener listener);

  public Object[] getSelectedValues();
}
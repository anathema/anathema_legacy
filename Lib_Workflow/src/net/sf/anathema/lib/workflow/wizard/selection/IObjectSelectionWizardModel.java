package net.sf.anathema.lib.workflow.wizard.selection;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IObjectSelectionWizardModel<T> {
  public void setSelectedObject(T newValue);

  public T getSelectedObject();

  public T[] getRegisteredObjects();

  public void addListener(IChangeListener inputListener);
}
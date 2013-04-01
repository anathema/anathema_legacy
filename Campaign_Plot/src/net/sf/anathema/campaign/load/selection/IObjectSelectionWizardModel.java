package net.sf.anathema.campaign.load.selection;

import net.sf.anathema.lib.control.IChangeListener;

public interface IObjectSelectionWizardModel<T> {
  void setSelectedObject(T newValue);

  T getSelectedObject();

  T[] getRegisteredObjects();

  void addListener(IChangeListener inputListener);
}
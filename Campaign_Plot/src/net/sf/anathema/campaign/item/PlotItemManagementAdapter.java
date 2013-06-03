package net.sf.anathema.campaign.item;

import net.sf.anathema.framework.repository.IItem;

public abstract class PlotItemManagementAdapter implements PlotItemManagementListener {

  @Override
  public void itemAdded(IItem item) {
    // nothing to do
  }

  @Override
  public void itemSelected(IItem item) {
    // nothing to do
  }

  @Override
  public void itemRemoved(IItem item) {
    // nothing to do
  }
}
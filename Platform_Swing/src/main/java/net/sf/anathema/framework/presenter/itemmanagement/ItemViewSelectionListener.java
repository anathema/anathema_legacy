package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.presenter.IItemManagementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.ItemView;

public class ItemViewSelectionListener implements IViewSelectionListener {

  private final IItemManagementModel model;
  private final IModelViewMapping mapping;

  public ItemViewSelectionListener(IItemManagementModel model, IModelViewMapping mapping) {
    this.model = model;
    this.mapping = mapping;
  }

  @Override
  public void viewSelectionChangedTo(ItemView view) {
    model.setSelectedItem(mapping.getModelByView(view));
  }
}

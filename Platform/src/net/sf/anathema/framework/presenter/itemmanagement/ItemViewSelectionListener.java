package net.sf.anathema.framework.presenter.itemmanagement;

import net.sf.anathema.framework.presenter.IItemMangementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;

public class ItemViewSelectionListener implements IViewSelectionListener {

  private final IItemMangementModel model;
  private final IModelViewMapping mapping;

  public ItemViewSelectionListener(IItemMangementModel model, IModelViewMapping mapping) {
    this.model = model;
    this.mapping = mapping;
  }

  public void viewSelectionChangedTo(IItemView view) {
    model.setSelectedItem(mapping.getModelByView(view));
  }
}
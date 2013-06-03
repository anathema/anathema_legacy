package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.ItemView;

public class PlotViewSelectionListener implements IViewSelectionListener {

  private final PlotItemManagement model;
  private final IModelViewMapping mapping;

  public PlotViewSelectionListener(PlotItemManagement model, IModelViewMapping mapping) {
    this.model = model;
    this.mapping = mapping;
  }

  @Override
  public void viewSelectionChangedTo(ItemView view) {
    model.setSelectedItem(mapping.getModelByView(view));
  }
}

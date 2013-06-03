package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.IItemManagementModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.ItemView;

public class CampaignViewSelectionListener implements IViewSelectionListener {

  private final IItemManagementModel model;
  private final IModelViewMapping mapping;

  public CampaignViewSelectionListener(IItemManagementModel model, IModelViewMapping mapping) {
    this.model = model;
    this.mapping = mapping;
  }

  @Override
  public void viewSelectionChangedTo(ItemView view) {
    model.setSelectedItem(mapping.getModelByView(view));
  }
}

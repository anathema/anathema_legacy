package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.lib.resources.Resources;

public class PlotViewListening {

  public void init(Resources resources, IApplicationModel model, IItemViewManagement itemViewManagement) {
    IModelViewMapping mapping = new ModelViewMapping();
    PlotItemManagement itemManagement = PlotItemManagementExtension.getItemManagement(model);
    PlotActionFactory actionFactory = new PlotActionFactory(itemManagement, resources);
    itemManagement.addListener(new PlotManagementModelListener(model.getViewFactoryRegistry(), itemViewManagement, mapping, actionFactory));
    itemViewManagement.addViewSelectionListener(new PlotViewSelectionListener(itemManagement, mapping));
  }
}

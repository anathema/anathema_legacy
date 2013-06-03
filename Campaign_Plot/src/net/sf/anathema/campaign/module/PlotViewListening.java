package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagement;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.ModelViewMapping;
import net.sf.anathema.framework.presenter.PlotItemViewFactory;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

public class PlotViewListening {

  public void init(Resources resources, IApplicationModel model, IItemViewManagement itemViewManagement) {
    IModelViewMapping mapping = new ModelViewMapping();
    PlotItemManagement itemManagement = PlotExtension.getItemManagement(model);
    PlotActionFactory actionFactory = new PlotActionFactory(itemManagement, resources);
    IRegistry<IItemType, PlotItemViewFactory> viewFactoryRegistry = PlotExtension.getExtension(model).getViewFactoryRegistry();
    itemManagement.addListener(new PlotManagementModelListener(viewFactoryRegistry, itemViewManagement, mapping, actionFactory));
    itemViewManagement.addViewSelectionListener(new PlotViewSelectionListener(itemManagement, mapping));
  }
}

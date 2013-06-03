package net.sf.anathema.campaign.module;

import net.sf.anathema.campaign.item.PlotItemManagementListener;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.model.IItemActionFactory;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.presenter.PlotItemViewFactory;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.framework.view.ItemView;
import net.sf.anathema.framework.view.SwingItemView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;

public class PlotManagementModelListener implements PlotItemManagementListener {

  private final IModelViewMapping mapping;
  private final IRegistry<IItemType, PlotItemViewFactory> viewFactoryRegistry;
  private final IItemViewManagement itemViewManagement;
  private final IItemActionFactory actionFactory;

  public PlotManagementModelListener(IRegistry<IItemType, PlotItemViewFactory> viewFactoryRegistry, IItemViewManagement itemViewManagement,
                                     IModelViewMapping mappping, IItemActionFactory actionFactory) {
    this.itemViewManagement = itemViewManagement;
    this.mapping = mappping;
    this.viewFactoryRegistry = viewFactoryRegistry;
    this.actionFactory = actionFactory;

  }

  @Override
  public void itemAdded(IItem item) throws AnathemaException {
    PlotItemViewFactory viewFactory = viewFactoryRegistry.get(item.getItemType());
    SwingItemView itemView = (SwingItemView) viewFactory.createView(item);
    mapping.addModelAndView(item, itemView);
    itemViewManagement.addItemView(itemView, actionFactory.createAction(item));
  }

  @Override
  public void itemSelected(IItem item) {
    if (item == null) {
      return;
    }
    ItemView view = mapping.getViewByModel(item);
    itemViewManagement.setSelectedItemView((SwingItemView) view);
  }

  @Override
  public void itemRemoved(IItem item) {
    ItemView view = mapping.getViewByModel(item);
    mapping.removeModelAndView(item, view);
    itemViewManagement.removeItemView((SwingItemView) view);
  }
}
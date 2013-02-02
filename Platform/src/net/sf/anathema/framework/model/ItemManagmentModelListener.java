package net.sf.anathema.framework.model;

import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.presenter.IItemManagementModelListener;
import net.sf.anathema.framework.presenter.IItemViewFactory;
import net.sf.anathema.framework.presenter.IModelViewMapping;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.registry.IRegistry;

public class ItemManagmentModelListener implements IItemManagementModelListener {

  private final IModelViewMapping mapping;
  private final IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry;
  private final MainView anathemaView;
  private final IItemActionFactory actionFactory;

  public ItemManagmentModelListener(
      IRegistry<IItemType, IItemViewFactory> viewFactoryRegistry,
      MainView anathemaView,
      IModelViewMapping mappping,
      IItemActionFactory actionFactory) {
    this.anathemaView = anathemaView;
    this.mapping = mappping;
    this.viewFactoryRegistry = viewFactoryRegistry;
    this.actionFactory = actionFactory;

  }

  @Override
  public void itemAdded(IItem item) throws AnathemaException {
    IItemViewFactory viewFactory = viewFactoryRegistry.get(item.getItemType());
    IItemView itemView = viewFactory.createView(item);
    mapping.addModelAndView(item, itemView);
    anathemaView.getItemViewManagement().addItemView(itemView, actionFactory.createAction(item));
  }

  @Override
  public void itemSelected(IItem item) {
    if (item == null) {
      return;
    }
    anathemaView.getItemViewManagement().setSelectedItemView(mapping.getViewByModel(item));
  }

  @Override
  public void itemRemoved(IItem item) {
    IItemView view = mapping.getViewByModel(item);
    mapping.removeModelAndView(item, view);
    anathemaView.getItemViewManagement().removeItemView(view);
  }
}
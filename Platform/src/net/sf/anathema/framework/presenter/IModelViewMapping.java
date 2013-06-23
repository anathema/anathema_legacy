package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.ItemView;

public interface IModelViewMapping {

  Item getModelByView(ItemView view);

  void addModelAndView(Item model, ItemView view);

  ItemView getViewByModel(Item model);

  void removeModelAndView(Item item, ItemView view);
}
package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.ItemView;

public interface IModelViewMapping {

  IItem getModelByView(ItemView view);

  void addModelAndView(IItem model, ItemView view);

  ItemView getViewByModel(IItem model);

  void removeModelAndView(IItem item, ItemView view);
}
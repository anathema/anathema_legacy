package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemView;

public interface IModelViewMapping {

  IItem getModelByView(IItemView view);

  void addModelAndView(IItem model, IItemView view);

  IItemView getViewByModel(IItem model);

  void removeModelAndView(IItem item, IItemView view);
}
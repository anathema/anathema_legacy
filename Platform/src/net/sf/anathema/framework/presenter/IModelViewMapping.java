package net.sf.anathema.framework.presenter;

import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.view.IItemView;

public interface IModelViewMapping {

  public IItem getModelByView(IItemView view);

  public void addModelAndView(IItem model, IItemView view);

  public IItemView getViewByModel(IItem model);

  public void removeModelAndView(IItem item, IItemView view);
}
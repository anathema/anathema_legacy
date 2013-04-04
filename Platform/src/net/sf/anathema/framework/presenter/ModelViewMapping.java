package net.sf.anathema.framework.presenter;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.view.IItemView;

import java.util.HashMap;
import java.util.Map;

public class ModelViewMapping implements IModelViewMapping {

  private final Map<IItemView, IItem> modelByView = new HashMap<>();
  private final Map<IItem, IItemView> viewByModel = new HashMap<>();
  private final Map<IItem, IItemListener> listenersByModel = new HashMap<>();

  @Override
  public IItem getModelByView(IItemView view) {
    return modelByView.get(view);
  }

  @Override
  public synchronized void addModelAndView(IItem model, final IItemView view) {
    Preconditions.checkArgument(!viewByModel.containsKey(model), "Model already managed.");
    modelByView.put(view, model);
    IItemListener listener = new IItemListener() {
      @Override
      public void printNameChanged(String newName) {
        view.setName(newName);
      }
    };
    model.addItemListener(listener);
    listenersByModel.put(model, listener);
    viewByModel.put(model, view);
  }

  @Override
  public IItemView getViewByModel(IItem model) {
    return viewByModel.get(model);
  }

  @Override
  public void removeModelAndView(IItem model, IItemView view) {
    modelByView.remove(view);
    IItemListener listener = listenersByModel.get(model);
    model.removeItemListener(listener);
    listenersByModel.remove(model);
    viewByModel.remove(model);
  }
}
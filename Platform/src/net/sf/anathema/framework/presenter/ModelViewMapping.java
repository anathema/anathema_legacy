package net.sf.anathema.framework.presenter;

import java.util.HashMap;
import java.util.Map;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.framework.repository.IItem;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.view.IItemView;

public class ModelViewMapping implements IModelViewMapping {

  private final Map<IItemView, IItem> modelByView = new HashMap<IItemView, IItem>();
  private final Map<IItem, IItemView> viewByModel = new HashMap<IItem, IItemView>();
  private final Map<IItem, IItemListener> listenersByModel = new HashMap<IItem, IItemListener>();

  public IItem getModelByView(IItemView view) {
    return modelByView.get(view);
  }

  public synchronized void addModelAndView(IItem model, final IItemView view) {
    Ensure.ensureFalse("Model already managed.", viewByModel.containsKey(model)); //$NON-NLS-1$
    modelByView.put(view, model);
    IItemListener listener = new IItemListener() {
      public void printNameChanged(String newName) {
        view.setName(newName);
      }
    };
    model.addItemListener(listener);
    listenersByModel.put(model, listener);
    viewByModel.put(model, view);
  }

  public IItemView getViewByModel(IItem model) {
    return viewByModel.get(model);
  }

  public void removeModelAndView(IItem model, IItemView view) {
    modelByView.remove(view);
    IItemListener listener = listenersByModel.get(model);
    model.removeItemListener(listener);
    listenersByModel.remove(model);
    viewByModel.remove(model);
  }
}
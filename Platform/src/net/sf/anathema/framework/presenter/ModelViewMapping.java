package net.sf.anathema.framework.presenter;

import com.google.common.base.Preconditions;
import net.sf.anathema.framework.repository.IItemListener;
import net.sf.anathema.framework.repository.Item;
import net.sf.anathema.framework.view.ItemView;

import java.util.HashMap;
import java.util.Map;

public class ModelViewMapping implements IModelViewMapping {

  private final Map<ItemView, Item> modelByView = new HashMap<>();
  private final Map<Item, ItemView> viewByModel = new HashMap<>();
  private final Map<Item, IItemListener> listenersByModel = new HashMap<>();

  @Override
  public Item getModelByView(ItemView view) {
    return modelByView.get(view);
  }

  @Override
  public synchronized void addModelAndView(Item model, final ItemView view) {
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
  public ItemView getViewByModel(Item model) {
    return viewByModel.get(model);
  }

  @Override
  public void removeModelAndView(Item model, ItemView view) {
    modelByView.remove(view);
    IItemListener listener = listenersByModel.get(model);
    model.removeItemListener(listener);
    listenersByModel.remove(model);
    viewByModel.remove(model);
  }
}
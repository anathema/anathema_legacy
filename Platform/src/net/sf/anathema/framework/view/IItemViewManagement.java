package net.sf.anathema.framework.view;

import javax.swing.Action;

public interface IItemViewManagement {
  void addItemView(IItemView view, Action action);

  void addViewSelectionListener(IViewSelectionListener listener);

  void setSelectedItemView(IItemView view);

  void removeItemView(IItemView view);
}
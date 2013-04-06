package net.sf.anathema.framework.view;

import javax.swing.Action;

public interface IItemViewManagement {
  void addItemView(SwingItemView view, Action action);

  void addViewSelectionListener(IViewSelectionListener listener);

  void setSelectedItemView(SwingItemView view);

  void removeItemView(SwingItemView view);
}
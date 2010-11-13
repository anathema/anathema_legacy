package net.sf.anathema.framework.view;

import javax.swing.Action;

public interface IItemViewManagement {
  public void addItemView(final IItemView view, Action action);

  public void addViewSelectionListener(IViewSelectionListener listener);

  public void setSelectedItemView(IItemView view);

  public void removeItemView(IItemView view);
}
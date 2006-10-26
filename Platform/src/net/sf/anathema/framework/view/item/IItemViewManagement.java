package net.sf.anathema.framework.view.item;

import javax.swing.Action;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.lib.gui.IView;

public interface IItemViewManagement extends IView {
  public void addItemView(final IItemView view, Action action);

  public void addViewSelectionListener(IViewSelectionListener listener);

  public void removeViewSelectionListener(IViewSelectionListener listener);

  public void setSelectedItemView(IItemView view);

  public void removeItemView(IItemView view);
}
package net.sf.anathema.framework.view.item;

import java.awt.Component;

import javax.swing.Action;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IViewSelectionListener;

public interface IItemViewManagement {
  public abstract void addItemView(final IItemView view, Action action);

  public abstract Component getComponent();

  public abstract void addViewSelectionListener(IViewSelectionListener listener);

  public abstract void removeViewSelectionListener(IViewSelectionListener listener);

  public abstract void setSelectedItemView(IItemView view);

  public abstract void removeItemView(IItemView view);

  public abstract void setItemViewName(IItemView view, String name);
}
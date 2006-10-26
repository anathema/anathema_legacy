package net.sf.anathema.framework.view;

import javax.swing.Action;

import net.sf.anathema.framework.view.messaging.AnathemaStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface IAnathemaView {

  public void addItemView(IItemView view, Action action);

  public void addViewSelectionListener(IViewSelectionListener listener);

  public void removeViewSelectionListener(IViewSelectionListener listener);

  public void setSelectedItemView(IItemView view);

  public void removeItemView(IItemView view);

  public IMenuBar getMenuBar();

  public IAnathemaToolbar getToolbar();

  public void showFrame();

  public AnathemaStatusBar getStatusBar();
}
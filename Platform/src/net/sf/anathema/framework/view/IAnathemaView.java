package net.sf.anathema.framework.view;

import java.awt.event.WindowListener;

import javax.swing.Action;

import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface IAnathemaView {

  public IAnathemaMenu getMenuBar();
  
  public IAnathemaToolbar getToolbar();

  public void addItemView(IItemView view, Action action);

  public void addViewSelectionListener(IViewSelectionListener listener);

  void addWindowListener(WindowListener listener);

  public void removeItemView(IItemView view);

  public void removeViewSelectionListener(IViewSelectionListener listener);

  public void setSelectedItemView(IItemView view);

  public void showFrame();
}
package net.sf.anathema;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IMenuBar;
import net.sf.anathema.framework.view.IViewSelectionListener;
import net.sf.anathema.framework.view.MainView;
import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;

public class NullMainView implements MainView {
  @Override
  public IMenuBar getMenuBar() {
    return new NullMenuBar();
  }

  @Override
  public IAnathemaToolbar getToolbar() {
    return new NullToolbar();
  }

  @Override
  public IStatusBar getStatusBar() {
    return new NullStatusBar();
  }

  @Override
  public void addItemView(IItemView view, Action action) {
    //nothing to do;
  }

  @Override
  public void addViewSelectionListener(IViewSelectionListener listener) {
    //nothing to do;
  }

  @Override
  public void setSelectedItemView(IItemView view) {
    //nothing to do;
  }

  @Override
  public void removeItemView(IItemView view) {
    //nothing to do;
  }

  @Override
  public void show() {
    //nothing to do;
  }
}

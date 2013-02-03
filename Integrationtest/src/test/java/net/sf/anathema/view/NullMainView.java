package net.sf.anathema.view;

import net.sf.anathema.framework.view.*;
import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

import javax.swing.Action;

public class NullMainView implements MainView {

  @Override
  public IItemViewManagement getItemViewManagement() {
    return new NullItemViewManagement();
  }

  @Override
  public IWindow getWindow() {
    return new NullWindow();
  }

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
}
package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.messaging.AnathemaStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface IAnathemaView extends IItemViewManagement {

  public IMenuBar getMenuBar();

  public IAnathemaToolbar getToolbar();

  public void showFrame();

  public AnathemaStatusBar getStatusBar();
}
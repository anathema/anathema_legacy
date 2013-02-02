package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface MainView {

  IItemViewManagement getItemViewManagement();

  ApplicationFrame getWindow();

  IMenuBar getMenuBar();

  IAnathemaToolbar getToolbar();

  IStatusBar getStatusBar();
}
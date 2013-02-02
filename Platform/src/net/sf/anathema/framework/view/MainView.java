package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface MainView {

  IItemViewManagement getIntegratedItemViewManagement();

  IAnathemaToolbar getIntegratedToolbar();

  ApplicationFrame getWindow();

  MenuBar getMenuBar();

  StatusBar getStatusBar();
}
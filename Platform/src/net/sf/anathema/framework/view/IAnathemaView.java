package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.messaging.IAnathemaStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public interface IAnathemaView extends IItemViewManagement, IWindow {

  IMenuBar getMenuBar();

  IAnathemaToolbar getToolbar();

  IAnathemaStatusBar getStatusBar();
}
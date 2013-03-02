package net.sf.anathema.framework.view;

import net.sf.anathema.framework.view.messaging.StatusBar;

public interface ApplicationView {

  ApplicationFrame getWindow();

  MenuBar getMenuBar();

  StatusBar getStatusBar();
}
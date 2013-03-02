package net.sf.anathema.view;

import net.sf.anathema.framework.view.ApplicationFrame;
import net.sf.anathema.framework.view.MenuBar;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.initialization.ApplicationFrameView;

public class NullMainView implements ApplicationFrameView {

  @Override
  public ApplicationFrame getWindow() {
    return new NullWindow();
  }

  @Override
  public MenuBar getMenuBar() {
    return new NullMenuBar();
  }

  @Override
  public StatusBar getStatusBar() {
    return new NullStatusBar();
  }

  @Override
  public void show() {
    //nothing to do
  }
}
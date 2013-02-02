package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public class AnathemaMainView implements MainView {

  private final SwingApplicationFrame applicationFrame;

  public AnathemaMainView(AnathemaViewProperties properties, ViewFactory contentFactory) {
     this.applicationFrame = new SwingApplicationFrame(properties, contentFactory);
  }

  @Override
  public StatusBar getStatusBar() {
    return applicationFrame.getStatusBar();
  }

  @Override
  public ApplicationFrame getWindow() {
    return applicationFrame;
  }

  @Override
  public MenuBar getMenuBar() {
    return applicationFrame.getMenuBar();
  }
}
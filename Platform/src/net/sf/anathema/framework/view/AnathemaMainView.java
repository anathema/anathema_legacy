package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.messaging.StatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public class AnathemaMainView implements MainView {

  private final IntegratedSystemView integratedSystemView = new IntegratedSystemView();
  private final SwingApplicationFrame applicationFrame;

  public AnathemaMainView(AnathemaViewProperties properties) {
     this.applicationFrame = new SwingApplicationFrame(properties, integratedSystemView);
  }

  @Override
  public StatusBar getStatusBar() {
    return applicationFrame.getStatusBar();
  }

  @Override
  public IAnathemaToolbar getIntegratedToolbar() {
    return integratedSystemView.getToolBar();
  }

  @Override
  public IItemViewManagement getIntegratedItemViewManagement() {
    return integratedSystemView;
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
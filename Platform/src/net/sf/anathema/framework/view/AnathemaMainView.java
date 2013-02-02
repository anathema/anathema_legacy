package net.sf.anathema.framework.view;

import net.sf.anathema.framework.presenter.AnathemaViewProperties;
import net.sf.anathema.framework.view.messaging.IStatusBar;
import net.sf.anathema.framework.view.toolbar.IAnathemaToolbar;

public class AnathemaMainView implements MainView {

  private final TabbedItemListView itemListView = new TabbedItemListView();
  private final SwingApplicationFrame applicationFrame;

  public AnathemaMainView(AnathemaViewProperties properties) {
     this.applicationFrame = new SwingApplicationFrame(properties, itemListView);
  }

  @Override
  public IStatusBar getStatusBar() {
    return applicationFrame.getStatusBar();
  }

  @Override
  public IAnathemaToolbar getToolbar() {
    return itemListView.getToolBar();
  }

  @Override
  public IItemViewManagement getItemViewManagement() {
    return itemListView;
  }

  @Override
  public ApplicationFrame getWindow() {
    return applicationFrame;
  }

  @Override
  public IMenuBar getMenuBar() {
    return applicationFrame.getMenuBar();
  }
}
package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.TabProperties;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

public class ViewTabContent implements ITabContent {

  private final IView contentView;
  private final TabProperties tabProperties;

  public ViewTabContent(IView contnetView, TabProperties tabProperties) {
    this.contentView = contnetView;
    this.tabProperties = tabProperties;
  }

  public void addTo(IMultiTabView view) {
    view.addTabView(this.contentView, tabProperties);
  }

  public IDisposable getDisposable() {
    return null;
  }
}
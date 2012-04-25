package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

public class ViewTabContent implements IViewContent {

  private final IView contentView;
  private final ContentProperties tabProperties;

  public ViewTabContent(IView contnetView, ContentProperties tabProperties) {
    this.contentView = contnetView;
    this.tabProperties = tabProperties;
  }

  @Override
  public void addTo(IMultiContentView view) {
    view.addView(this.contentView, tabProperties);
  }

  @Override
  public IDisposable getDisposable() {
    return null;
  }
}
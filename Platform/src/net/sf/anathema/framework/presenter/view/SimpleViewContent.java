package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

public class SimpleViewContent implements IViewContent {

  private final ContentProperties properties;
  private final IView tabView;

  public SimpleViewContent(ContentProperties properties, IView tabView) {
    this.properties = properties;
    this.tabView = tabView;
  }

  public void addTo(IMultiContentView view) {
    view.addView(tabView, properties);
  }

  public IDisposable getDisposable() {
    return tabView instanceof IDisposable ? (IDisposable) tabView : null;
  }
}
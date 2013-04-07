package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.swing.IView;

public class SimpleViewContentView implements ContentView {

  private final ContentProperties properties;
  private final IView tabView;

  public SimpleViewContentView(ContentProperties properties, IView tabView) {
    this.properties = properties;
    this.tabView = tabView;
  }

  @Override
  public void addTo(MultipleContentView view) {
    view.addView(tabView, properties);
  }
}
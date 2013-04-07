package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.swing.IView;

public class ViewTabContentView implements ContentView {

  private final IView contentView;
  private final ContentProperties tabProperties;

  public ViewTabContentView(IView view, ContentProperties tabProperties) {
    this.contentView = view;
    this.tabProperties = tabProperties;
  }

  @Override
  public void addTo(MultipleContentView view) {
    view.addView(this.contentView, tabProperties);
  }
}
package net.sf.anathema.character.presenter;

import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.lib.gui.IDisposable;

public class TabContent {
  
  private final String header;
  private final ISimpleTabView tabView;

  public TabContent(String header, ISimpleTabView tabView) {
    this.header = header;
    this.tabView = tabView;
  }

  public void addTo(IMultiTabView view) {
    view.addTabView(tabView, header);
  }

  public IDisposable getDisposable() {
    return tabView instanceof IDisposable ? (IDisposable) tabView : null;
  }
}
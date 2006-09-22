package net.sf.anathema.character.presenter;

import net.sf.anathema.framework.presenter.view.IMultiTabView;
import net.sf.anathema.framework.presenter.view.ISimpleTabView;
import net.sf.anathema.framework.view.util.TabProperties;
import net.sf.anathema.lib.gui.IDisposable;

public class SimpleViewTabContent implements ITabContent {

  private final String header;
  private final ISimpleTabView tabView;

  public SimpleViewTabContent(String header, ISimpleTabView tabView) {
    this.header = header;
    this.tabView = tabView;
  }

  /* (non-Javadoc)
   * @see net.sf.anathema.character.presenter.ITabContent#addTo(net.sf.anathema.framework.presenter.view.IMultiTabView)
   */
  public void addTo(IMultiTabView view) {
    TabProperties tabProperties = new TabProperties(header);
    if (tabView.needsScrollbar()) {
      tabProperties = tabProperties.needsScrollbar();
    }
    view.addTabView(tabView, tabProperties);
  }

  /* (non-Javadoc)
   * @see net.sf.anathema.character.presenter.ITabContent#getDisposable()
   */
  public IDisposable getDisposable() {
    return tabView instanceof IDisposable ? (IDisposable) tabView : null;
  }
}
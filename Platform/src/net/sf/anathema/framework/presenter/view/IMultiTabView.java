package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.TabProperties;
import net.sf.anathema.lib.gui.IView;

public interface IMultiTabView extends ITabView<Object> {

  public void addTabView(ISimpleTabView view, String tabName);

  public void addTabView(IView view, TabProperties tabProperties);
}
package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

public interface IMultiContentView extends ITabView<Object> {

  public void addTabView(IView view, ContentProperties tabProperties);
}
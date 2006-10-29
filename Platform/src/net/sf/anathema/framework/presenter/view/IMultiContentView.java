package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

public interface IMultiContentView extends IContentView<Object> {

  public void addView(IView view, ContentProperties tabProperties);
}
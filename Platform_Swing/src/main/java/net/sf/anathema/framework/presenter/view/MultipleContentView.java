package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;

public interface MultipleContentView {

  void addView(IView view, ContentProperties tabProperties);
}
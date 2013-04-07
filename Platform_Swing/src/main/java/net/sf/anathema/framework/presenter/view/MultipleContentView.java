package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.framework.swing.IView;

public interface MultipleContentView extends IView {

  void addView(IView view, ContentProperties tabProperties);

}
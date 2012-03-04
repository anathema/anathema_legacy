package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

import javax.swing.JComponent;

public interface IMultiContentView extends IView {

  void addView(IView view, ContentProperties tabProperties);

  void setAdditionalComponent(JComponent components);
}
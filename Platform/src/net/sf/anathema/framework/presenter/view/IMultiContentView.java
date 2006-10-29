package net.sf.anathema.framework.presenter.view;

import javax.swing.JComponent;

import net.sf.anathema.framework.view.util.ContentProperties;
import net.sf.anathema.lib.gui.IView;

public interface IMultiContentView extends IView {

  public void addView(IView view, ContentProperties tabProperties);

  public void setAdditionalComponents(JComponent[] components);
}
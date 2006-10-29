package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.IView;

public interface ITabView<P> extends IView {

  public void initGui(P properties);
}
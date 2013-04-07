package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.framework.swing.IView;

public interface IInitializableContentView<P> extends IView {

  void initGui(P properties);
}
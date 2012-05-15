package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.swing.IDisposable;

public interface IViewContent {

  void addTo(MultipleContentView view);

  IDisposable getDisposable();
}
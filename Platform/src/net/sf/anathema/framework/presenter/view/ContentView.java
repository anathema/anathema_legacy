package net.sf.anathema.framework.presenter.view;

import net.sf.anathema.lib.gui.swing.IDisposable;

public interface ContentView {

  void addTo(MultipleContentView view);

  IDisposable getDisposable();
}
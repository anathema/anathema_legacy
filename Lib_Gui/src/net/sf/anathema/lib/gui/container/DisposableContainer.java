package net.sf.anathema.lib.gui.container;

import net.sf.anathema.lib.gui.swing.AggregatedDisposable;
import net.sf.anathema.lib.gui.swing.IDisposable;

public class DisposableContainer implements IDisposable {

  private final AggregatedDisposable disposables = new AggregatedDisposable();

  protected final void addDisposable(IDisposable disposable) {
    disposables.add(disposable);
  }

  @Override
  public void dispose() {
    disposables.dispose();
  }
}
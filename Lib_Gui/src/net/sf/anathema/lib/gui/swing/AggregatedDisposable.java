package net.sf.anathema.lib.gui.swing;

import java.util.ArrayList;
import java.util.List;


public class AggregatedDisposable implements IDisposable {

  private final List<IDisposable> allDisposables = new ArrayList<IDisposable>();

  @Override
  public void dispose() {
    for (final IDisposable disposable : allDisposables) {
      disposable.dispose();
    }
  }

  public void add(final IDisposable disposable) {
    allDisposables.add(disposable);
  }
}
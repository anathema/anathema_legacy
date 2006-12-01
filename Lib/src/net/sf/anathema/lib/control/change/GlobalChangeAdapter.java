package net.sf.anathema.lib.control.change;

import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class GlobalChangeAdapter<T> implements IObjectValueChangedListener<T> {

  private final IChangeListener listener;

  public GlobalChangeAdapter(IChangeListener listener) {
    this.listener = listener;
  }

  public void valueChanged(T newValue) {
    listener.changeOccured();
  }
}
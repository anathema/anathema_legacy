package net.sf.anathema.lib.control.change;

import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public class GlobalChangeAdapter<T> implements
    IObjectValueChangedListener<T>,
    IIntValueChangedListener, IBooleanValueChangedListener {

  private final IChangeListener listener;

  public GlobalChangeAdapter(IChangeListener listener) {
    this.listener = listener;
  }

  @Override
  public void valueChanged(T newValue) {
    listener.changeOccurred();
  }

  @Override
  public void valueChanged(int newValue) {
    listener.changeOccurred();
  }

  @Override
  public void valueChanged(boolean newValue) {
    listener.changeOccurred();
  }
}
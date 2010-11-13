package net.sf.anathema.lib.control;

public abstract class AbstractListenerClosure<T> implements IClosure<T> {

  private final GenericControl<T> control;

  public AbstractListenerClosure(GenericControl<T> control) {
    this.control = control;
  }

  public void fireEvent() {
    control.forAllDo(this);
  }
}
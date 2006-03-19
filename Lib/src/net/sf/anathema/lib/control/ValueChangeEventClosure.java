package net.sf.anathema.lib.control;


public final class ValueChangeEventClosure implements IClosure<IChangeListener> {
  public void execute(IChangeListener input) {
    input.changeOccured();
  }
}
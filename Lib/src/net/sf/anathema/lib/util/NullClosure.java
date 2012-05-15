package net.sf.anathema.lib.util;

public class NullClosure<T> implements Closure<T> {

  @Override
  public void execute(final T each) {
    // nothing to do
  }
}
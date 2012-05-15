package net.sf.anathema.lib.util;

public class NullClosure<T> implements Closure<T> {

  @Override
  public void execute(T each) {
    // nothing to do
  }
}
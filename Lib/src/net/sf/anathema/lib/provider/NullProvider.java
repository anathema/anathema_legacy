package net.sf.anathema.lib.provider;

public class NullProvider<T> implements Provider<T> {

  @Override
  public T getObject() {
    return null;
  }
}
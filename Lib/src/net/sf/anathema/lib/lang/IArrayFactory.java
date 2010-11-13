package net.sf.anathema.lib.lang;

public interface IArrayFactory<V> {

  public V[] createArray(int length);
}
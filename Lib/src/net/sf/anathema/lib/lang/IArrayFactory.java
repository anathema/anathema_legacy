package net.sf.anathema.lib.lang;

public interface IArrayFactory<V> {

  V[] createArray(int length);
}
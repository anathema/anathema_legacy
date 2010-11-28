package net.sf.anathema.lib.lang;

import java.lang.reflect.Array;

public class ArrayFactory<V> implements IArrayFactory<V> {

  private final Class<V> componentType;

  public ArrayFactory(Class<V> componentType) {
    this.componentType = componentType;
  }

  @SuppressWarnings("unchecked")
  public V[] createArray(int length) {
    return (V[]) Array.newInstance(componentType, length);
  }
}
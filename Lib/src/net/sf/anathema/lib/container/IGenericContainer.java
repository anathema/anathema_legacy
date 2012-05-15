package net.sf.anathema.lib.container;

public interface IGenericContainer<V> extends IGenericArrayProvider<V> {

  void setValues(V[] values);
}
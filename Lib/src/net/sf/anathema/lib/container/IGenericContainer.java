package net.sf.anathema.lib.container;

public interface IGenericContainer<V> extends IGenericArrayProvider<V> {

  public void setValues(V[] values);
}
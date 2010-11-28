package net.sf.anathema.lib.container;

public interface IGenericSelectionContainer<V> extends IGenericContainer<V> {

  public V[] getAllAvailableValues();
}
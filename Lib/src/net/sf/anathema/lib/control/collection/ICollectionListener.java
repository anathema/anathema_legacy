package net.sf.anathema.lib.control.collection;

public interface ICollectionListener<T> {

  public void itemAdded(T item);
  
  public void itemRemoved(T item);
}
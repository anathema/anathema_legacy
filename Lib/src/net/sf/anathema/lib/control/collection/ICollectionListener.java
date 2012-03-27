package net.sf.anathema.lib.control.collection;

public interface ICollectionListener<T> {

  void itemAdded(T item);

  void itemRemoved(T item);
}
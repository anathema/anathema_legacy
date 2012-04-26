package net.sf.anathema.lib.control;

public interface ICollectionListener<T> {

  void itemAdded(T item);

  void itemRemoved(T item);
}
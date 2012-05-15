package net.sf.anathema.lib.model;

public interface IProperty<T> {

  T getValue();

  void setValue(T value);

}
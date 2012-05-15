package net.sf.anathema.lib.model;

public interface IProperty<T> {

  public T getValue();

  public void setValue(T value);

}
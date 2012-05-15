package net.sf.anathema.lib.model;

import net.sf.anathema.lib.util.ObjectUtilities;

public class DefaultProperty<T> implements IProperty<T> {

  private T value;

  @Override
  public T getValue() {
    return value;
  }

  @Override
  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof DefaultProperty)) {
      return false;
    }
    DefaultProperty<?> other = (DefaultProperty<?>) object;
    return ObjectUtilities.equals(value, other.value);
  }

  @Override
  public int hashCode() {
    return ObjectUtilities.getHashCode(value);
  }
}
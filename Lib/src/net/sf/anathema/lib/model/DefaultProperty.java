package net.sf.anathema.lib.model;

import com.google.common.base.Objects;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    return Objects.equal(value, other.value);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(value).build();
  }
}
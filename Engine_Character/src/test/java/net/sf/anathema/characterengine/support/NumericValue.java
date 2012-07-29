package net.sf.anathema.characterengine.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NumericValue {
  private int value;

  public NumericValue(int value) {
    this.value = value;
  }

  public void changeBy(NumericValue modification) {
    this.value += modification.value;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }

  @Override
  public String toString() {
    return "'" + value + "'";
  }
}
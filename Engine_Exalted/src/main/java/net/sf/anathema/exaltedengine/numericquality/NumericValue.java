package net.sf.anathema.exaltedengine.numericquality;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class NumericValue {
  private int value;

  public NumericValue(int value) {
    this.value = value;
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

  public boolean isGreaterThan(NumericValue valueForComparison) {
    return this.value > valueForComparison.value;
  }

  public void changeTo(NumericValue newValue) {
    this.value = newValue.value;
  }

  public NumericValue copy() {
    return new NumericValue(value);
  }
}
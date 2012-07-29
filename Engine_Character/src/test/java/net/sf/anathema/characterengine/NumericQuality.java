package net.sf.anathema.characterengine;

public class NumericQuality implements Quality {
  private final NumericValue value;

  public NumericQuality(NumericValue value) {
    this.value = value;
  }

  public boolean hasValue(NumericValue value) {
    return value.equals(this.value);
  }
}

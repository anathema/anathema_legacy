package net.sf.anathema.exaltedengine.numericquality;

public interface QualityWithValue {
  boolean hasValue(NumericValue numericValue);

  void changeValueTo(NumericValue numericValue);
}
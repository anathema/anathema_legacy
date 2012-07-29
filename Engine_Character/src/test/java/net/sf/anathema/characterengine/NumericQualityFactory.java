package net.sf.anathema.characterengine;

public class NumericQualityFactory implements QualityFactory{
  private final int startValue;

  public NumericQualityFactory(int startValue) {
    this.startValue = startValue;
  }

  @Override
  public Quality create() {
    return new NumericQuality(new NumericValue(startValue));
  }
}

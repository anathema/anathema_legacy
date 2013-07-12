package net.sf.anathema.hero.advance.creation.basic;

public class SimpleCostElement implements ICostElement {

  private final int zeroCalculationValue;
  private final int calculationValue;

  public SimpleCostElement(int zeroCalculationValue, int calculationValue) {
    this.zeroCalculationValue = zeroCalculationValue;
    this.calculationValue = calculationValue;
  }

  @Override
  public int getCalculationValue() {
    return calculationValue;
  }

  @Override
  public int getZeroCalculationValue() {
    return zeroCalculationValue;
  }
}
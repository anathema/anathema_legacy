package net.sf.anathema.hero.attributes.advance.creation;

public class SimpleCostElement implements CostElement {

  private final int baseValue;
  private final int calculationValue;

  public SimpleCostElement(int baseValue, int calculationValue) {
    this.baseValue = baseValue;
    this.calculationValue = calculationValue;
  }

  @Override
  public int getCalculationValue() {
    return calculationValue;
  }

  @Override
  public int getBaseValue() {
    return baseValue;
  }
}
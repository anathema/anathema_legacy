package net.sf.anathema.hero.spiritual.model.pool;

public class FactorizedTraitSumCalculator {

  public int calculateSum(FactorizedTrait[] factorizedTraits) {
    int overallSum = 0;
    for (FactorizedTrait factorizedTrait : factorizedTraits) {
      overallSum += factorizedTrait.getCalculateTotal();
    }
    return overallSum;
  }
}
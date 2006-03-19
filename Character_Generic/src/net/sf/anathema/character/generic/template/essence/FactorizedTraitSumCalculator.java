package net.sf.anathema.character.generic.template.essence;

public class FactorizedTraitSumCalculator {

  public int calculateSum(FactorizedTrait[] factorizedTraits) {
    int overallSum = 0;
    for (FactorizedTrait factorizedTrait : factorizedTraits) {
      overallSum += factorizedTrait.getCalculateTotal();
    }
    return overallSum;
  }
}
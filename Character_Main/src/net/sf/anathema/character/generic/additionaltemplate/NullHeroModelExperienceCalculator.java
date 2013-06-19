package net.sf.anathema.character.generic.additionaltemplate;

public class NullHeroModelExperienceCalculator implements HeroModelExperienceCalculator {
  @Override
  public int calculateCost() {
    return 0;
  }

  @Override
  public int calculateGain() {
    return 0;
  }
}
package net.sf.anathema.character.generic.additionaltemplate;

public abstract class AbstractAdditionalModelAdapter implements IAdditionalModel {

  @Override
  public HeroModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  @Override
  public HeroModelExperienceCalculator getExperienceCalculator() {
    return new NullHeroModelExperienceCalculator();
  }
}
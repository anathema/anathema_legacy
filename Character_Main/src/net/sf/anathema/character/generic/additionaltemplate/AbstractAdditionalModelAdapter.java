package net.sf.anathema.character.generic.additionaltemplate;

public abstract class AbstractAdditionalModelAdapter implements IAdditionalModel {

  @Override
  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  @Override
  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }
}
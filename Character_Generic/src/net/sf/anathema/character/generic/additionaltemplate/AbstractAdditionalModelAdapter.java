package net.sf.anathema.character.generic.additionaltemplate;

public abstract class AbstractAdditionalModelAdapter implements IAdditionalModel {

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }
}
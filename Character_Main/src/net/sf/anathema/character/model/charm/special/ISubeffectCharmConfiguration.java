package net.sf.anathema.character.model.charm.special;

public interface ISubeffectCharmConfiguration extends IMultipleEffectCharmConfiguration {

  public int getCreationLearnedSubeffectCount();

  public int getExperienceLearnedSubeffectCount();

  public double getPointCostPerEffect();
}
package net.sf.anathema.character.model.charm.special;

public interface ISubeffectCharmConfiguration extends IMultipleEffectCharmConfiguration {

  int getCreationLearnedSubeffectCount();

  int getExperienceLearnedSubeffectCount();

  double getPointCostPerEffect();
}
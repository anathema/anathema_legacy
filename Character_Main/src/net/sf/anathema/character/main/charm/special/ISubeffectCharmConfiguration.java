package net.sf.anathema.character.main.charm.special;

public interface ISubeffectCharmConfiguration extends IMultipleEffectCharmConfiguration {

  int getCreationLearnedSubeffectCount();

  int getExperienceLearnedSubeffectCount();

  double getPointCostPerEffect();
}
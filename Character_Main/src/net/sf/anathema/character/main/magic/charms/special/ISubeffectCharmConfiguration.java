package net.sf.anathema.character.main.magic.charms.special;

public interface ISubeffectCharmConfiguration extends IMultipleEffectCharmConfiguration {

  int getCreationLearnedSubeffectCount();

  int getExperienceLearnedSubeffectCount();

  double getPointCostPerEffect();
}
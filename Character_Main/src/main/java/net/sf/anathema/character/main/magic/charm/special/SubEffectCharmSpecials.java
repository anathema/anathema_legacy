package net.sf.anathema.character.main.magic.charm.special;

public interface SubEffectCharmSpecials extends MultipleEffectCharmSpecials {

  int getCreationLearnedSubEffectCount();

  int getExperienceLearnedSubEffectCount();

  double getPointCostPerEffect();
}
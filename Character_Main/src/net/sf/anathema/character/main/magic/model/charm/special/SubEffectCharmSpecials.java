package net.sf.anathema.character.main.magic.model.charm.special;

public interface SubEffectCharmSpecials extends MultipleEffectCharmSpecials {

  int getCreationLearnedSubEffectCount();

  int getExperienceLearnedSubEffectCount();

  double getPointCostPerEffect();
}
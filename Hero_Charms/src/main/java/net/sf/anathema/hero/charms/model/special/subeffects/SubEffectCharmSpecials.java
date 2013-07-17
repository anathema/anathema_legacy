package net.sf.anathema.hero.charms.model.special.subeffects;

public interface SubEffectCharmSpecials extends MultipleEffectCharmSpecials {

  int getCreationLearnedSubEffectCount();

  int getExperienceLearnedSubEffectCount();

  double getPointCostPerEffect();
}
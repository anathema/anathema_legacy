package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.hero.concept.CasteType;

public interface CharmsRules {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();
}
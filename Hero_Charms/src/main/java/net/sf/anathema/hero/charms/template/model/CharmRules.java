package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.hero.concept.CasteType;

public interface CharmRules {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();
}
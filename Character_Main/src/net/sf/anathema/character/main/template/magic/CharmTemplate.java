package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.hero.concept.CasteType;

public interface CharmTemplate {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();
}
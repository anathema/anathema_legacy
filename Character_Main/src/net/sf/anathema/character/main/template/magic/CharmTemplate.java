package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.hero.concept.CasteType;

public interface CharmTemplate {
  MartialArtsRules getMartialArtsRules();

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);
}

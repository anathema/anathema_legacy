package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.hero.concept.CasteType;

public interface ICharmTemplate extends ICharmSet {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();
}
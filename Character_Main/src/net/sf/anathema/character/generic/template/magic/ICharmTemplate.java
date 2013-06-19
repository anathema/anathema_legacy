package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.caste.CasteType;

public interface ICharmTemplate extends ICharmSet {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(CasteType caste);

  MartialArtsRules getMartialArtsRules();
}
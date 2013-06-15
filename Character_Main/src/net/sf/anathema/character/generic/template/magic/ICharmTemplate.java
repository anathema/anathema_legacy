package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;

public interface ICharmTemplate extends ICharmSet {

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(ICasteType caste);

  MartialArtsRules getMartialArtsRules();
}
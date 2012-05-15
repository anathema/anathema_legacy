package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;

public interface ICharmTemplate extends ICharmSet {
  IUniqueCharmType getUniqueCharmType();

  boolean hasUniqueCharms();

  boolean canLearnCharms();

  boolean isAllowedAlienCharms(ICasteType caste);

  IMartialArtsRules getMartialArtsRules();
}
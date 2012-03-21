package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface ICharmTemplate {
	
  ICharm[] getCharms(IExaltedRuleSet rules);
  
  ICharm[] getUniqueCharms(IExaltedRuleSet rules);

  ICharm[] getMartialArtsCharms(IExaltedRuleSet rules);
  
  IUniqueCharmType getUniqueCharmType();
  
  boolean hasUniqueCharms();

  boolean canLearnCharms(IExaltedRuleSet rules);

  boolean isAllowedAlienCharms(ICasteType caste);

  IMartialArtsRules getMartialArtsRules();
}
package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface ICharmTemplate {

  public ICharm[] getCharms(IExaltedRuleSet rules);

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet rules);
  
  public IUniqueRequiredCharmType getUniqueRequiredCharmType();

  public boolean canLearnCharms(IExaltedRuleSet rules);

  public boolean isAllowedAlienCharms(ICasteType caste);

  public IMartialArtsRules getMartialArtsRules();
}
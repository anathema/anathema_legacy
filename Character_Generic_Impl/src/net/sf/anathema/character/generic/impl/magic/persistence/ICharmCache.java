package net.sf.anathema.character.generic.impl.magic.persistence;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.lib.util.IIdentificate;

public interface ICharmCache {
  public ICharm[] getCharms(IIdentificate type, IExaltedRuleSet ruleset);
  
  public ISpecialCharm[] getSpecialCharmData(IIdentificate type, IExaltedRuleSet rules);
  
  public String getCharmRename(IExaltedRuleSet rules, String name);
}
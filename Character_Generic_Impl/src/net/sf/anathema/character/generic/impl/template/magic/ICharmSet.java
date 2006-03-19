package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface ICharmSet {

  public ICharm[] getCharms(IExaltedRuleSet set);

  public IMartialArtsCharm[] getMartialArtsCharms(IExaltedRuleSet set);

}
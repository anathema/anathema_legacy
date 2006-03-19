package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMartialArtsCharm;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public class NullCharmSet implements ICharmSet {

  public ICharm[] getCharms(IExaltedRuleSet set) {
    return new ICharm[0];
  }

  public IMartialArtsCharm[] getMartialArtsCharms(IExaltedRuleSet set) {
    return new IMartialArtsCharm[0];
  }
}
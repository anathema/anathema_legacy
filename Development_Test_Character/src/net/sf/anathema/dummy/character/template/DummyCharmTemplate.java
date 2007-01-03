package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;

public class DummyCharmTemplate implements ICharmTemplate {

  public ICharm[] getCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public boolean knowsCharms(IExaltedRuleSet rules) {
    return false;
  }

  public IMartialArtsRules getMartialArtsRules() {
    return new DefaultMartialArtsRules(MartialArtsLevel.Mortal, true);
  }

  public boolean isAllowedAlienCharms(ICasteType caste) {
    return false;
  }
}
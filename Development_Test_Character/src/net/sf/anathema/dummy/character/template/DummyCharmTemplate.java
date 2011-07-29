package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedRuleSet;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMartialArtsRules;
import net.sf.anathema.character.generic.template.magic.IUniqueRequiredCharmType;

public class DummyCharmTemplate implements ICharmTemplate {

  public ICharm[] getCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public ICharm[] getMartialArtsCharms(IExaltedRuleSet rules) {
    return new ICharm[0];
  }

  public boolean canLearnCharms(IExaltedRuleSet rules) {
    return false;
  }

  public IMartialArtsRules getMartialArtsRules() {
    DefaultMartialArtsRules defaultMartialArtsRules = new DefaultMartialArtsRules(MartialArtsLevel.Mortal);
    defaultMartialArtsRules.setHighLevelAtCreation(true);
    return defaultMartialArtsRules;
  }

  public boolean isAllowedAlienCharms(ICasteType caste) {
    return false;
  }

	@Override
	public IUniqueRequiredCharmType getUniqueRequiredCharmType() {
		return null;
	}
}
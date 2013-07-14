package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.hero.charms.template.model.CharmsTemplate;
import net.sf.anathema.hero.concept.CasteType;

public class CharmsRulesImpl implements CharmsRules {

  private CharmsTemplate tto;

  public CharmsRulesImpl(CharmsTemplate tto) {
    this.tto = tto;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    return new MartialArtsRulesImpl(tto.martialArts);
  }

  @Override
  public boolean canLearnCharms() {
    return !"None".equals(tto.charmType);
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return tto.charmType.contains(caste.getId());
  }
}
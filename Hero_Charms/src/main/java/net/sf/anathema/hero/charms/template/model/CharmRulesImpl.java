package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.hero.concept.CasteType;

public class CharmRulesImpl implements CharmRules {

  private CharmTto tto;

  public CharmRulesImpl(CharmTto tto) {
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
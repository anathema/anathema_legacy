package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.template.magic.MartialArtsRulesImpl;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public class DummyCharmTemplate implements CharmTemplate {

  @Override
  public boolean canLearnCharms() {
    return false;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    MartialArtsRulesImpl defaultMartialArtsRules = new MartialArtsRulesImpl(MartialArtsLevel.Mortal);
    defaultMartialArtsRules.setHighLevelAtCreation(true);
    return defaultMartialArtsRules;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return false;
  }
}
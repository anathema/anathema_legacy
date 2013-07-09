package net.sf.anathema.character.main.testing.dummy.template;

import net.sf.anathema.character.main.caste.CasteType;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.template.magic.ICharmTemplate;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;

public class DummyCharmTemplate implements ICharmTemplate {

  @Override
  public Charm[] getCharms() {
    return new Charm[0];
  }

  @Override
  public Charm[] getMartialArtsCharms() {
    return new Charm[0];
  }

  @Override
  public boolean canLearnCharms() {
    return false;
  }

  @Override
  public MartialArtsRules getMartialArtsRules() {
    DefaultMartialArtsRules defaultMartialArtsRules = new DefaultMartialArtsRules(MartialArtsLevel.Mortal);
    defaultMartialArtsRules.setHighLevelAtCreation(true);
    return defaultMartialArtsRules;
  }

  @Override
  public boolean isAllowedAlienCharms(CasteType caste) {
    return false;
  }
}
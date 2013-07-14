package net.sf.anathema.hero.dummy.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.template.magic.MartialArtsRules;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public class DummyMartialArtsRules implements MartialArtsRules {

  @Override
  public boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced) {
    return false;
  }

  @Override
  public MartialArtsLevel getStandardLevel() {
    return null;
  }

  @Override
  public void setHighLevelAtCreation(boolean highLevelAtCreation) {
    //nothing to do
  }
}
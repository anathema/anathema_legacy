package net.sf.anathema.character.generic.dummy.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.template.magic.MartialArtsCharmConfiguration;
import net.sf.anathema.character.generic.template.magic.MartialArtsRules;

public class DummyMartialArtsRules implements MartialArtsRules {

  @Override
  public boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration, boolean isExperienced) {
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
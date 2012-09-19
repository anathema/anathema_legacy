package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration, boolean isExperienced);

  MartialArtsLevel getStandardLevel();

  void setHighLevelAtCreation(boolean highLevelAtCreation);
}
package net.sf.anathema.character.generic.template.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;

public interface IMartialArtsRules {

  boolean isCharmAllowed(ICharm martialArtsCharm, IGenericCharmConfiguration charmConfiguration, boolean isExperienced);

  MartialArtsLevel getStandardLevel();

  void setHighLevelAtCreation(boolean highLevelAtCreation);
}
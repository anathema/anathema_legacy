package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(ICharm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration, boolean isExperienced);

  MartialArtsLevel getStandardLevel();

  void setHighLevelAtCreation(boolean highLevelAtCreation);
}
package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, MartialArtsCharmConfiguration charmConfiguration, boolean isExperienced);

  MartialArtsLevel getStandardLevel();

  void setHighLevelAtCreation(boolean highLevelAtCreation);
}
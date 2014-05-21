package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.character.magic.charm.Charm;
import net.sf.anathema.character.magic.charm.martial.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced);

  MartialArtsLevel getStandardLevel();
}
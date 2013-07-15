package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.charms.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced);

  MartialArtsLevel getStandardLevel();
}
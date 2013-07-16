package net.sf.anathema.hero.charms.model.rules;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced);

  MartialArtsLevel getStandardLevel();
}
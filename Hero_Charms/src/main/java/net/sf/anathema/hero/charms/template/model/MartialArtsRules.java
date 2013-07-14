package net.sf.anathema.hero.charms.template.model;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public interface MartialArtsRules {

  boolean isCharmAllowed(Charm martialArtsCharm, boolean isExperienced);

  MartialArtsLevel getStandardLevel();
}
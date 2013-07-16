package net.sf.anathema.character.main.magic.charmtree;

import net.sf.anathema.character.main.magic.charm.Charm;

public interface ICharmLearnableArbitrator {

  boolean isLearnable(Charm charm);
}
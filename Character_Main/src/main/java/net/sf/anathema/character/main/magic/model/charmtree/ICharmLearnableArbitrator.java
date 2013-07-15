package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.Charm;

public interface ICharmLearnableArbitrator {

  boolean isLearnable(Charm charm);
}
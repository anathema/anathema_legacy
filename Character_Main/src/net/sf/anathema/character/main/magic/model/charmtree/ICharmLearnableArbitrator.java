package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.ICharm;

public interface ICharmLearnableArbitrator {
  boolean isLearnable(ICharm charm);
}
package net.sf.anathema.character.generic.impl.magic.charm.prerequisite;

import net.sf.anathema.character.generic.impl.magic.Charm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  public void forget(Charm charm);
}
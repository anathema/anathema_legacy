package net.sf.anathema.character.main.magic.charm.prerequisite;

import net.sf.anathema.character.main.magic.Charm;
import net.sf.anathema.character.main.magic.charms.ICharmLearnArbitrator;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  void forget(Charm charm);
}
package net.sf.anathema.character.main.magic.model.charm.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  void forget(Charm charm);
}
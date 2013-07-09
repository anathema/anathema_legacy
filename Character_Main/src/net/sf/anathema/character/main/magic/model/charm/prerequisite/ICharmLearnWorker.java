package net.sf.anathema.character.main.magic.model.charm.prerequisite;

import net.sf.anathema.character.main.magic.model.charm.CharmImpl;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  void forget(CharmImpl charm);
}
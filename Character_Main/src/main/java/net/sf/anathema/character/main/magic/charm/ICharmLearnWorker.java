package net.sf.anathema.character.main.magic.charm;

import net.sf.anathema.character.main.magic.charm.CharmImpl;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public interface ICharmLearnWorker extends ICharmLearnArbitrator {

  void forget(CharmImpl charm);
}
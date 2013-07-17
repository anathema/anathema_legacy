package net.sf.anathema.hero.charms.model;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}